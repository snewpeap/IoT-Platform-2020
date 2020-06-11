package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplateTopic;
import edu.nju.se.yrd.iotconnmgmt.entity.Protocol;
import edu.nju.se.yrd.iotconnmgmt.repository.DeviceTemplateTopicRepository;
import edu.nju.se.yrd.iotconnmgmt.repository.ProtocolRepository;
import edu.nju.se.yrd.iotconnmgmt.service.DeviceTemplateTopicService;
import edu.nju.se.yrd.iotconnmgmt.util.TopicValidator;
import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicForm;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

@Service
public class DeviceTemplateTopicServiceImpl implements DeviceTemplateTopicService {
    private final DeviceTemplateTopicRepository deviceTemplateTopicRepository;
    private final ProtocolRepository protocolRepository;

    @Autowired
    public DeviceTemplateTopicServiceImpl(DeviceTemplateTopicRepository deviceTemplateTopicRepository, ProtocolRepository protocolRepository) {
        this.deviceTemplateTopicRepository = deviceTemplateTopicRepository;
        this.protocolRepository = protocolRepository;
    }

    @Override
    public CarryPayloadResponse<List<DeviceTemplateTopicVO>> getTopics(String deviceTemplateId) {
        Assert.notNull(deviceTemplateId, "设备模板ID不能为空");
        List<DeviceTemplateTopic> deviceTemplateTopics = deviceTemplateTopicRepository.getByHost_Id(deviceTemplateId);
        List<DeviceTemplateTopicVO> payload = new LinkedList<>();
        deviceTemplateTopics.forEach(entity -> payload.add(DeviceTemplateTopicVO.convertFromEntity(entity)));
        return CarryPayloadResponse.ok(payload);
    }

    @Override
    public BasicResponse addTopic(DeviceTemplateTopicForm form) {
        Assert.notNull(form, "表单为空");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DeviceTemplateTopicForm>> validate = validator.validate(form);
        BasicResponse response;

        String topic;
        Optional<Protocol> optionalProtocol = Optional.empty();
        StringJoiner messageJoiner = new StringJoiner("\n");
        if (validate.isEmpty()) {
            if (!form.getInbound() && !form.getOutbound()) {
                messageJoiner.add("至少选择一个通信方向");
            } else if (form.getOutbound()) {
                try {
                    TopicValidator.isValid(form.getName(), false);
                } catch (Exception e) {
                    messageJoiner.add("出向不允许使用通配符");
                }
            }

            optionalProtocol = protocolRepository.findByName(form.getProtocol());
            if (optionalProtocol.isPresent()) {
                topic = "/" + form.getDeviceTemplateId() + "/${deviceName}/user" + form.getName();
                if (deviceTemplateTopicRepository.existsByName(topic)) {
                    messageJoiner.add("已经存在的模板Topic");
                }
            } else {
                messageJoiner.add("协议不存在");
            }
        } else {
            validate.stream().map(ConstraintViolation::getMessage).forEach(messageJoiner::add);
        }

        if (messageJoiner.length() == 0) {
            DeviceTemplateTopic deviceTemplateTopic = form.convertToEntity();
            optionalProtocol.ifPresent(deviceTemplateTopic::setProtocol);
            deviceTemplateTopicRepository.save(deviceTemplateTopic);
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
}
