package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplateTopic;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTopic;
import edu.nju.se.yrd.iotconnmgmt.entity.Protocol;
import edu.nju.se.yrd.iotconnmgmt.repository.DeviceTopicRepository;
import edu.nju.se.yrd.iotconnmgmt.repository.ProtocolRepository;
import edu.nju.se.yrd.iotconnmgmt.service.DeviceTopicService;
import edu.nju.se.yrd.iotconnmgmt.util.TopicTool;
import edu.nju.se.yrd.iotconnmgmt.util.TopicValidator;
import edu.nju.se.yrd.iotconnmgmt.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;
import java.util.concurrent.Future;

@Service
public class DeviceTopicServiceImpl implements DeviceTopicService {
    private final DeviceTopicRepository deviceTopicRepository;
    private final ProtocolRepository protocolRepository;

    @Autowired
    public DeviceTopicServiceImpl(DeviceTopicRepository deviceTopicRepository, ProtocolRepository protocolRepository) {
        this.deviceTopicRepository = deviceTopicRepository;
        this.protocolRepository = protocolRepository;
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
            TopicValidator.isValid(form.getName(), form.getInbound(), form.getOutbound(), messageJoiner);

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
                topic = TopicTool.generateName(deviceTemplateId, deviceId, form.getName());
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
            deviceTopicRepository.save(deviceTopic);
            response = BasicResponse.ok();
        } else {
            response = BasicResponse.error().message(messageJoiner.toString());
        }
        return response;
    }

    @Override
    public BasicResponse removeTopic(Long topicId) {
        return null;
    }

    @Override
    @Async
    public Future<CarryPayloadResponse<String>> sendMessage(Long topicId, String message, boolean async) {
        return null;
    }

    @Override
    public CarryPayloadResponse<List<MessageVO>> getMessages(Long topicId) {
        return null;
    }
}
