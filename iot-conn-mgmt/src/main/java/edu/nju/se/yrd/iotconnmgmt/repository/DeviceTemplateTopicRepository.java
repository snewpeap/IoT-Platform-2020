package edu.nju.se.yrd.iotconnmgmt.repository;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplateTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceTemplateTopicRepository extends JpaRepository<DeviceTemplateTopic, Long> {
    /**
     * 根据设备模板Id获取设备模板的模板Topic
     *
     * @param deviceTemplateId 设备模板Id
     * @return 模板Topic列表
     */
    List<DeviceTemplateTopic> getByHost_Id(String deviceTemplateId);


    /**
     * 通过设备模板Id获取某一个（头一个）模板Topic
     *
     * @param host_id 设备模板Id
     * @return 这个设备模板的某一个（头一个）模板Topic
     */
    Optional<DeviceTemplateTopic> getFirstByHost_Id(String host_id);

    /**
     * 查询是否存在同名的模板Topic
     *
     * @param name 模板Topic名（路径）
     * @return 是否有同名
     */
    boolean existsByName(String name);
}
