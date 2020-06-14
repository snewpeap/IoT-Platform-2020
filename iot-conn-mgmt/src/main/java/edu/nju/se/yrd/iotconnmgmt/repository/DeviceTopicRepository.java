package edu.nju.se.yrd.iotconnmgmt.repository;

import edu.nju.se.yrd.iotconnmgmt.entity.Device;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeviceTopicRepository extends JpaRepository<DeviceTopic, Long> {
    /**
     * 通过设备Id获得设备的Topic
     *
     * @param host_id 设备Id
     * @return 设备的Topic列表
     */
    List<DeviceTopic> getByHost_Id(String host_id);

    /**
     * 通过设备Id获取某一个（头一个）Topic
     *
     * @param host_id 设备Id
     * @return 这个设备的某一个（头一个）Topic
     */
    Optional<DeviceTopic> getFirstByHost_Id(String host_id);

    @Query("select distinct dt.host from DeviceTopic dt where dt.host.template.id = ?1 ")
    List<Device> getDistinctDevice(String templateId);

    /**
     * 查询是否存在同名的Topic
     *
     * @param name Topic名（路径）
     * @return 是否有同名
     */
    Boolean existsByName(String name);

    Optional<DeviceTopic> getByName(String name);
}
