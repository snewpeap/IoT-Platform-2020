package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplateTopic;

import java.util.StringJoiner;

public class DeviceTemplateTopicVO {
    private Long id;
    private String deviceTemplateName;
    private String name;
    private String description;
    private Boolean outbound;
    private Boolean inbound;

    public DeviceTemplateTopicVO(Long id, String deviceTemplateName, String name, String description, Boolean outbound, Boolean inbound) {
        this.id = id;
        this.deviceTemplateName = deviceTemplateName;
        this.name = name;
        this.description = description;
        this.outbound = outbound;
        this.inbound = inbound;
    }

    public DeviceTemplateTopicVO() {
        super();
    }

    public static DeviceTemplateTopicVO convertFromEntity(DeviceTemplateTopic entity) {
        return new DeviceTemplateTopicVO(
                entity.getId(),
                entity.getHost().getName(),
                entity.getName(),
                entity.getDescription(),
                entity.getOutbound(),
                entity.getInbound()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceTemplateName() {
        return deviceTemplateName;
    }

    public void setDeviceTemplateName(String deviceTemplateName) {
        this.deviceTemplateName = deviceTemplateName;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", DeviceTemplateTopicVO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deviceTemplateName='" + deviceTemplateName + "'")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("outbound=" + outbound)
                .add("inbound=" + inbound)
                .toString();
    }
}
