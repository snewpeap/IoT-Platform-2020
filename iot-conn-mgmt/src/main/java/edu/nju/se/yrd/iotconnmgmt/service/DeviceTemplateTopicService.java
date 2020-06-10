package edu.nju.se.yrd.iotconnmgmt.service;

import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicForm;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicVO;

import java.util.List;

public interface DeviceTemplateTopicService {

    /**
     * 获得一个设备模板的所有模板Topic
     *
     * @param deviceTemplateId 设备模板在平台上的Id
     * @return 包含模板Topic列表的Response
     */
    CarryPayloadResponse<List<DeviceTemplateTopicVO>> getTopics(Long deviceTemplateId);

    /**
     * 为设备模板增加一个模板Topic
     *
     * @param form 新增模板Topic表单
     * @return 增加模板Topic结果
     */
    BasicResponse addTopic(DeviceTemplateTopicForm form);

    /**
     * 为设备模板删除一个模板Topic
     *
     * @param topicId 要删除的模板Topic在平台上的Id
     * @return 删除模板Topic的结果
     */
    BasicResponse removeTopic(Long topicId);
}
