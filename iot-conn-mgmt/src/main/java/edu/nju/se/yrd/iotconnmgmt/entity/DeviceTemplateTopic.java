package edu.nju.se.yrd.iotconnmgmt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DeviceTemplateTopic {
    @Id
    @GeneratedValue
    private Long id;
    private DeviceTemplate host;
    private String name;
    private String description;
    private Boolean upstream;
    private Boolean downstream;
    @ManyToOne
    private Protocol protocol;

    public DeviceTemplateTopic(DeviceTemplate host, String name, String description, Boolean upstream, Boolean downstream) {
        this.host = host;
        this.name = name;
        this.description = description;
        this.upstream = upstream;
        this.downstream = downstream;
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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
}
