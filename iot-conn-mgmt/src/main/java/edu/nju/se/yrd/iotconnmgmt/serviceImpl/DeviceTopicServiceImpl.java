package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import edu.nju.se.yrd.iotconnmgmt.service.DeviceTopicService;
import edu.nju.se.yrd.iotconnmgmt.vo.*;

import java.util.List;
import java.util.concurrent.Future;

public class DeviceTopicServiceImpl implements DeviceTopicService {
    @Override
    public CarryPayloadResponse<List<DeviceTemplateTopicVO>> getTopics(Long deviceId) {
        return null;
    }

    @Override
    public BasicResponse addTopic(DeviceTopicForm form) {
        return null;
    }

    @Override
    public BasicResponse removeTopic(Long topicId) {
        return null;
    }

    @Override
    public Future<CarryPayloadResponse<String>> sendMessage(Long topicId, String message, boolean async) {
        return null;
    }

    @Override
    public CarryPayloadResponse<List<MessageVO>> getMessages(Long topicId) {
        return null;
    }
}
