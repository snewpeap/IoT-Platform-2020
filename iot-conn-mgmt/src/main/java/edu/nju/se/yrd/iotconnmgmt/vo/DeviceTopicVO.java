package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTopic;

public class DeviceTopicVO {
    private Long id;
    private String deviceTemplateName;
    private String name;
    private String description;
    private Boolean upstream;
    private Boolean downstream;

    public DeviceTopicVO(Long id, String deviceTemplateName, String name, String description, Boolean upstream, Boolean downstream) {
        this.id = id;
        this.deviceTemplateName = deviceTemplateName;
        this.name = name;
        this.description = description;
        this.upstream = upstream;
        this.downstream = downstream;
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
                entity.getUpstream(),
                entity.getDownstream()
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
}
