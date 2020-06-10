package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplate;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplateTopic;

public class DeviceTemplateTopicForm {
    private Long deviceTemplateId;
    private String name;
    private String description;
    private Boolean upstream;
    private Boolean downstream;
    private String protocol;

    public Long getDeviceTemplateId() {
        return deviceTemplateId;
    }

    public void setDeviceTemplateId(Long deviceTemplateId) {
        this.deviceTemplateId = deviceTemplateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getUpstream() {
        return upstream;
    }

    public void setUpstream(Boolean upstream) {
        this.upstream = upstream;
    }

    public Boolean getDownstream() {
        return downstream;
    }

    public void setDownstream(Boolean downstream) {
        this.downstream = downstream;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /* 转换后的Entity没有处理protocol信息，需要在后续处理 */
    public DeviceTemplateTopic convertToEntity() {
        DeviceTemplate deviceTemplate = new DeviceTemplate(deviceTemplateId, deviceTemplateId.toString());
        return new DeviceTemplateTopic(deviceTemplate, name, description, upstream, downstream);
    }
}