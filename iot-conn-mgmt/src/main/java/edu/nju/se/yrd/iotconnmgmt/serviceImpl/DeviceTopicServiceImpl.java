package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import edu.nju.se.yrd.iotconnmgmt.entity.*;
import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;
import edu.nju.se.yrd.iotconnmgmt.repository.DeviceTopicRepository;
import edu.nju.se.yrd.iotconnmgmt.repository.MessageRepository;
import edu.nju.se.yrd.iotconnmgmt.repository.ProtocolRepository;
import edu.nju.se.yrd.iotconnmgmt.service.DeviceTopicService;
import edu.nju.se.yrd.iotconnmgmt.service.ProtocolService;
import edu.nju.se.yrd.iotconnmgmt.util.TopicTool;
import edu.nju.se.yrd.iotconnmgmt.util.TopicValidator;
import edu.nju.se.yrd.iotconnmgmt.vo.*;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeviceTopicServiceImpl implements DeviceTopicService {
    private final DeviceTopicRepository deviceTopicRepository;
    private final ProtocolRepository protocolRepository;
    private final MessageRepository messageRepository;
    private final ProtocolService protocolService;
    @Autowired
    private AsyncMessageSender asyncMessageSender;

    @Autowired
    public DeviceTopicServiceImpl(DeviceTopicRepository deviceTopicRepository, ProtocolRepository protocolRepository,
                                  MessageRepository messageRepository, ProtocolService protocolService) {
        this.deviceTopicRepository = deviceTopicRepository;
        this.protocolRepository = protocolRepository;
        this.messageRepository = messageRepository;
        this.protocolService = protocolService;
    }

    @PostConstruct
    void postConstruct() {
        protocolService.register(this);
        deviceTopicRepository.findAll().forEach(topic -> {
            try {
                protocolService.getProtocolInstance(topic.getProtocol().getName()).subscribe(topic.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public CarryPayloadResponse<List<DeviceTopicVO>> getTopics(String deviceId) {
        Assert.notNull(deviceId, "设备ID不能为空");
        List<DeviceTopicVO> payload = new LinkedList<>();
        deviceTopicRepository.getByHost_Id(deviceId).forEach(entity -> payload.add(DeviceTopicVO.convertFromEntity(entity)));
        return CarryPayloadResponse.ok(payload);
    }

    @Override
    public BasicResponse addTopic(DeviceTopicForm form) {
        Assert.notNull(form, "表单为空");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DeviceTopicForm>> validate = validator.validate(form);

        BasicResponse response;
        String topic = "";
        Optional<Protocol> optionalProtocol = Optional.empty();
        StringJoiner messageJoiner = new StringJoiner("\n");
        if (validate.isEmpty()) {
            String name = TopicTool.startWithSlash(form.getName());
            TopicValidator.isValid(name, form.getInbound(), form.getOutbound(), messageJoiner);

            optionalProtocol = protocolRepository.findByName(form.getProtocol());
            if (!optionalProtocol.isPresent()) {
                messageJoiner.add("协议不存在");
            }

            Optional<DeviceTopic> checkHost = deviceTopicRepository.getFirstByHost_Id(form.getDeviceId());
            if (!checkHost.isPresent()) {
                messageJoiner.add("不存在的设备");
            } else {
                String deviceTemplateId = checkHost.get().getHost().getTemplate().getId();
                String deviceId = checkHost.get().getHost().getId();
                topic = TopicTool.generateName(deviceTemplateId, deviceId, name);
                if (deviceTopicRepository.existsByName(topic)) {
                    messageJoiner.add("已经存在的Topic");
                }
            }
        } else {
            //validation检测出不符合条件的字段，一股脑加进错误信息里
            validate.stream().map(ConstraintViolation::getMessage).forEach(messageJoiner::add);
        }

        if (messageJoiner.length() == 0) {
            DeviceTopic deviceTopic = form.convertToEntity();
            deviceTopic.setParent(null); //自定义Topic没有模板
            deviceTopic.setName(topic);
            optionalProtocol.ifPresent(deviceTopic::setProtocol);
            response = addTopic(deviceTopic) ? BasicResponse.ok() : BasicResponse.error().message("添加Topic时出现问题");
        } else {
            response = BasicResponse.error().message(messageJoiner.toString());
        }
        return response;
    }

    @Override
    public int addTopic(DeviceTemplateTopic templateTopic) {
        int failure = 0;
        List<Device> distinctDevice = deviceTopicRepository.getDistinctDevice(templateTopic.getHost().getId());
        for (Device device : distinctDevice) {
            DeviceTopic deviceTopic = new DeviceTopic();
            deviceTopic.setName(TopicTool.generateTopicFromTemplate(device.getId(), templateTopic.getName()));
            deviceTopic.setHost(device);
            deviceTopic.setParent(templateTopic);
            deviceTopic.setDescription("Auto generated from template topic.");
            deviceTopic.setInbound(templateTopic.getInbound());
            deviceTopic.setOutbound(templateTopic.getOutbound());
            deviceTopic.setProtocol(templateTopic.getProtocol());
            if (!addTopic(deviceTopic)) {
                failure++;
            }
        }
        return failure;
    }

    private boolean addTopic(DeviceTopic deviceTopic) {
        try {
            if (protocolService.getProtocolInstance(deviceTopic.getProtocol().getName()).subscribe(deviceTopic.getName())) {
                deviceTopicRepository.save(deviceTopic);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BasicResponse removeTopic(Long topicId) {
        return null;
    }

    @Override
    public BasicResponse sendMessage(Long topicId, String message) {
        Assert.notNull(topicId, "TopicId不能为空");
        Assert.hasText(message, "不能发送空白消息");

        Optional<DeviceTopic> topicOptional = deviceTopicRepository.findById(topicId);
        if (topicOptional.isPresent()) {
            DeviceTopic topic = topicOptional.get();
            if (!topic.getInbound()) {
                return BasicResponse.error().message("非出向Topic，不能发送消息");
            }
            Assert.notNull(topic.getProtocol(), "Topic居然没有Protocol");
            IProtocol protocolInstance = protocolService.getProtocolInstance(topic.getProtocol().getName());
            if (protocolInstance == null) {
                return BasicResponse.error().message("该协议暂不可用");
            }
            asyncMessageSender.send(protocolInstance, topic, message);
        }
        return BasicResponse.ok();
    }

    @Override
    public CarryPayloadResponse<List<MessageVO>> getMessages(Long topicId) {
        Assert.notNull(topicId, "TopicID不能为空");
        return CarryPayloadResponse
                .ok(messageRepository.getByTopic_IdOrderByTimestampDesc(topicId)
                        .stream().map(MessageVO::convertFromEntity).collect(Collectors.toList()));
    }

    @Override
    public void update(Observable o, Object arg) {
        String[] received = (String[]) arg;
        String topic = received[0];
        String message = received[1];

        for (DeviceTopic deviceTopic : deviceTopicRepository.findAll()) {
            if (deviceTopic.getName().equals(topic) || MqttTopic.isMatched(deviceTopic.getName(), topic)) {
                Message messageEntity = new Message();
                messageEntity.setTopic(deviceTopic);
                messageEntity.setContent(message);
                messageEntity.setDirection(Message.DIRECTION.INBOUND);
                messageEntity.setStatus(Message.STATUS.RECEIVED);
                messageEntity.setTimestamp(System.currentTimeMillis());
                messageRepository.save(messageEntity);
            }
        }
    }

    @Component
    class AsyncMessageSender {
        @Async
        void send(IProtocol protocol, DeviceTopic topic, String message) {
            Message messageEntity = new Message();
            messageEntity.setTopic(topic);
            messageEntity.setContent(message);
            messageEntity.setStatus(Message.STATUS.SENDING);
            messageEntity.setDirection(Message.DIRECTION.OUTBOUND);
            messageEntity.setTimestamp(System.currentTimeMillis());
            messageRepository.save(messageEntity);

            try {
                messageEntity.setStatus(protocol.send(topic.getName(), message) ? Message.STATUS.SENT : Message.STATUS.FAILED);
            } catch (Exception e) {
                e.printStackTrace();
                messageEntity.setStatus(Message.STATUS.FAILED);
            } finally {
                messageEntity.setTimestamp(System.currentTimeMillis());
                messageRepository.save(messageEntity);
            }
        }
    }
}
