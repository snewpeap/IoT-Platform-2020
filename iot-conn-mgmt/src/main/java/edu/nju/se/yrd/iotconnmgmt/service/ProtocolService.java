package edu.nju.se.yrd.iotconnmgmt.service;

import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;
import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.ProtocolVO;

import java.io.File;
import java.util.List;

public interface ProtocolService {
    IProtocol getProtocolInstance(String name);

    CarryPayloadResponse<List<ProtocolVO>> getDetailProtocols();

    /**
     * 获取平台当前支持的协议
     *
     * @return 平台当前支持的协议列表
     */
    CarryPayloadResponse<List<String>> getProtocols();

    /**
     * 用户上传新的协议实现程序（jar包）后，对程序进行验证、装载，并返回流程结果
     *
     * @param file 用户上传的jar包
     * @return 流程结果
     */
    BasicResponse newProtocol(File file);

    /**
     * 注册观察者
     *
     * @param deviceTopicService 作为DeviceTopicService的观察者
     */
    void register(DeviceTopicService deviceTopicService);
}
