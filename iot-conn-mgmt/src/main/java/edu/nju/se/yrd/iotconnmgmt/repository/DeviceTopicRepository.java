package edu.nju.se.yrd.iotconnmgmt.repository;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceTopicRepository extends JpaRepository<DeviceTopic, Long> {
    /**
     * 通过设备Id获得设备的Topic
     *
     * @param deviceId 设备Id
     * @return 设备的Topic列表
     */
    List<DeviceTopic> getByHost_Id(Long deviceId);

    /**
     * 查找是否存在以某个模板Topic为父Topic的Topic
     *
     * @param templateTopicId 模板Topic
     * @return 是否存在
     */
    Boolean existsByParent_Id(Long templateTopicId);


}
