package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTopic;

import java.util.StringJoiner;

public class DeviceTopicVO {
    private Long id;
    private String deviceName;
    private String name;
    private String description;
    private Boolean outbound;
    private Boolean inbound;
    private String protocol;

    public DeviceTopicVO(Long id, String deviceName, String name, String description, Boolean outbound, Boolean inbound, String protocol) {
        this.id = id;
        this.deviceName = deviceName;
        this.name = name;
        this.description = description;
        this.outbound = outbound;
        this.inbound = inbound;
        this.protocol = protocol;
    }

    public DeviceTopicVO() {
        super();
    }

    public static DeviceTopicVO convertFromEntity(DeviceTopic entity) {
        return new DeviceTopicVO(
                entity.getId(),
                entity.getHost().getName(),
                entity.getName(),
                entity.getDescription(),
                entity.getOutbound(),
                entity.getInbound(),
                entity.getProtocol().getName()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", DeviceTopicVO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deviceName='" + deviceName + "'")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("outbound=" + outbound)
                .add("inbound=" + inbound)
                .add("protocol='" + protocol + "'")
                .toString();
    }
}
