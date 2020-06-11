package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.Device;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTopic;

public class DeviceTopicForm {
    private Long deviceId;//TODO String
    private String name;
    private String description;
    private Boolean outbound;
    private Boolean inbound;
    private String protocol;
    private Long templateTopicId;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
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

    public Boolean getOutbound() {
        return outbound;
    }

    public void setOutbound(Boolean outbound) {
        this.outbound = outbound;
    }

    public Boolean getInbound() {
        return inbound;
    }

    public void setInbound(Boolean inbound) {
        this.inbound = inbound;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Long getTemplateTopicId() {
        return templateTopicId;
    }

    public void setTemplateTopicId(Long templateTopicId) {
        this.templateTopicId = templateTopicId;
    }

    /* 转换后的Entity没有处理protocol和parent信息，需要在后续处理 */
    public DeviceTopic convertToEntity() {
        Device device = new Device(deviceId, deviceId.toString());
        return new DeviceTopic(device, name, description, outbound, inbound);
    }
}
