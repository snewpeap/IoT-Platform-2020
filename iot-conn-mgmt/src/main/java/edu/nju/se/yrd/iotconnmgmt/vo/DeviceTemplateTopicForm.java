package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplate;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplateTopic;
import edu.nju.se.yrd.iotconnmgmt.util.TopicType;

import javax.validation.constraints.NotNull;

public class DeviceTemplateTopicForm {
    @NotNull
    private String deviceTemplateId;
    @TopicType
    private String name;
    @NotNull(message = "Topic描述不能为空")
    private String description;
    @NotNull(message = "出向标识不能为空")
    private Boolean outbound;
    @NotNull(message = "入向标识不能为空")
    private Boolean inbound;
    @NotNull(message = "协议不能为空")
    private String protocol;

    public String getDeviceTemplateId() {
        return deviceTemplateId;
    }

    public void setDeviceTemplateId(String deviceTemplateId) {
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

    /* 转换后的Entity没有处理protocol信息，需要在后续处理 */
    public DeviceTemplateTopic convertToEntity() {
        DeviceTemplate deviceTemplate = new DeviceTemplate(deviceTemplateId, deviceTemplateId);
        return new DeviceTemplateTopic(deviceTemplate, name, description, outbound, inbound);
    }
}
