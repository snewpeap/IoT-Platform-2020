package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import edu.nju.se.yrd.iotconnmgmt.service.DeviceTemplateTopicService;
import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicForm;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTemplateTopicServiceImpl implements DeviceTemplateTopicService {
    @Override
    public CarryPayloadResponse<List<DeviceTemplateTopicVO>> getTopics(Long deviceTemplateId) {
        return null;
    }

    @Override
    public BasicResponse addTopic(DeviceTemplateTopicForm form) {
        return null;
    }

    @Override
    public BasicResponse removeTopic(Long topicId) {
        return null;
    }
}
