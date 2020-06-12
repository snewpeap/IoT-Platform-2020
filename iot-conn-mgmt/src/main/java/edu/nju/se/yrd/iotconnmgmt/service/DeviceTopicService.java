package edu.nju.se.yrd.iotconnmgmt.service;

import edu.nju.se.yrd.iotconnmgmt.vo.*;

import java.util.List;
import java.util.concurrent.Future;

public interface DeviceTopicService {

    /**
     * 获得一个设备的所有Topic
     *
     * @param deviceId 设备在平台上的Id
     * @return 包含Topic列表的Response
     */
    CarryPayloadResponse<List<DeviceTopicVO>> getTopics(String deviceId);

    /**
     * 为设备增加一个Topic
     *
     * @param form 新增Topic表单
     * @return 增加Topic结果
     */
    BasicResponse addTopic(DeviceTopicForm form);

    /**
     * 为设备删除一个Topic
     *
     * @param topicId 要删除的Topic在平台上的Id
     * @return 删除Topic结果
     */
    BasicResponse removeTopic(Long topicId);

    /**
     * 向一个Topic发送一条同步/异步消息
     * 同步/异步只是对于该接口调用者而言，平台总是发送同步消息
     *
     * @param topicId 发送目标Topic的在平台上的Id
     * @param message 要发送的消息内容
     * @param async 是否发送异步消息
     * @return 消息发送的结果
     */
    Future<CarryPayloadResponse<String>> sendMessage(Long topicId, String message, boolean async);

    /**
     * 获取一个Topic的历史消息来往记录
     *
     * @param topicId 目标Topic在平台上的Id
     * @return 历史消息
     */
    CarryPayloadResponse<List<MessageVO>> getMessages(Long topicId);
}
