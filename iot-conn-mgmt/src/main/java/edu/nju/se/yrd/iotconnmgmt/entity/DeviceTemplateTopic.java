package edu.nju.se.yrd.iotconnmgmt.entity;

import javax.persistence.*;

@Entity
public class DeviceTemplateTopic {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "device_template_id")),
            @AttributeOverride(name = "name", column = @Column(name = "device_template_name"))
    })
    private DeviceTemplate host;
    private String name;
    private String description;
    private Boolean outbound;
    private Boolean inbound;
    @ManyToOne
    private Protocol protocol;

    public DeviceTemplateTopic(DeviceTemplate host, String name, String description, Boolean outbound, Boolean inbound) {
        this.host = host;
        this.name = name;
        this.description = description;
        this.outbound = outbound;
        this.inbound = inbound;
    }

    public DeviceTemplateTopic() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceTemplate getHost() {
        return host;
    }

    public void setHost(DeviceTemplate host) {
        this.host = host;
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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
}
