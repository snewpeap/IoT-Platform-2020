package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import com.github.javafaker.Faker;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplate;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplateTopic;
import edu.nju.se.yrd.iotconnmgmt.entity.Protocol;
import edu.nju.se.yrd.iotconnmgmt.repository.DeviceTemplateTopicRepository;
import edu.nju.se.yrd.iotconnmgmt.repository.ProtocolRepository;
import edu.nju.se.yrd.iotconnmgmt.service.DeviceTemplateTopicService;
import edu.nju.se.yrd.iotconnmgmt.service.DeviceTopicService;
import edu.nju.se.yrd.iotconnmgmt.util.TopicTool;
import edu.nju.se.yrd.iotconnmgmt.util.TopicValidator;
import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicForm;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

@Service
public class DeviceTemplateTopicServiceImpl implements DeviceTemplateTopicService {
    private final DeviceTemplateTopicRepository deviceTemplateTopicRepository;
    private final ProtocolRepository protocolRepository;
    private final DeviceTopicService deviceTopicService;

    @Autowired
    public DeviceTemplateTopicServiceImpl(DeviceTemplateTopicRepository deviceTemplateTopicRepository,
                                          ProtocolRepository protocolRepository,
                                          DeviceTopicService deviceTopicService) {
        this.deviceTemplateTopicRepository = deviceTemplateTopicRepository;
        this.protocolRepository = protocolRepository;
        this.deviceTopicService = deviceTopicService;
    }

    @PostConstruct
    void postConstruct() {
        Faker faker = new Faker();
        DeviceTemplate deviceTemplate = new DeviceTemplate("16ca8851-2d1a-4f8f-a84a-88566dc159dc", "No.1 Product");
        protocolRepository.findByName("MQTT").ifPresent(protocol -> {
            for (int i = 0; i < 15; i++) {
                DeviceTemplateTopic topic = new DeviceTemplateTopic();
                topic.setName(
                        String.format(
                                "/%s/%s",
                                faker.hacker().verb().replace(" ", ""),
                                faker.hacker().noun().replace(" ", "")
                        )
                );
                topic.setProtocol(protocol);
                topic.setInbound(faker.bool().bool());
                topic.setOutbound(!topic.getInbound() || faker.bool().bool());
                topic.setHost(deviceTemplate);
                topic.setDescription(faker.company().bs());
                deviceTemplateTopicRepository.save(topic);
            }
        });
    }

    @Override
    public CarryPayloadResponse<List<DeviceTemplateTopicVO>> getTopics(String deviceTemplateId) {
        Assert.notNull(deviceTemplateId, "设备模板ID不能为空");
        List<DeviceTemplateTopicVO> payload = new LinkedList<>();
        deviceTemplateTopicRepository.getByHost_Id(deviceTemplateId).forEach(entity -> payload.add(DeviceTemplateTopicVO.convertFromEntity(entity)));
        return CarryPayloadResponse.ok(payload);
    }

    @Override
    public BasicResponse addTopic(DeviceTemplateTopicForm form) {
        Assert.notNull(form, "表单为空");
        //没有使用@Valid，而在这里进行validation
        //主要是因为测试用例写好后才发现测试里@Valid不生效
        //这样不也挺好吗
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DeviceTemplateTopicForm>> validate = validator.validate(form);

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

            Optional<DeviceTemplateTopic> checkHost = deviceTemplateTopicRepository.getFirstByHost_Id(form.getDeviceTemplateId());
            if (!checkHost.isPresent()) {
                messageJoiner.add("不存在的设备模板");
            } else {
                topic = TopicTool.generateNameFromUserInput(form.getName(), form.getDeviceTemplateId());
                if (deviceTemplateTopicRepository.existsByName(topic)) {
                    messageJoiner.add("已经存在的模板Topic");
                }
            }
        } else {
            //validation检测出不符合条件的字段，一股脑加进错误信息里
            validate.stream().map(ConstraintViolation::getMessage).forEach(messageJoiner::add);
        }

        if (messageJoiner.length() == 0) {
            DeviceTemplateTopic deviceTemplateTopic = form.convertToEntity();
            deviceTemplateTopic.setName(topic);
            optionalProtocol.ifPresent(deviceTemplateTopic::setProtocol);
            deviceTemplateTopicRepository.save(deviceTemplateTopic);
            int failure = deviceTopicService.addTopic(deviceTemplateTopic);
            response = failure == 0 ?
                    BasicResponse.ok() :
                    BasicResponse.error().message("模板Topic添加成功，但有" + failure + "个设备添加Topic失败");
        } else {
            response = BasicResponse.error().message(messageJoiner.toString());
        }
        return response;
    }

    @Override
    public BasicResponse removeTopic(Long topicId) {
        return null;
    }
}
