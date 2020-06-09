package edu.nju.se.yrd.iotconnmgmt.entity;

import javax.persistence.Embeddable;

@Embeddable
public class DeviceTemplate {
    private Long id;
    private String name;

    public DeviceTemplate() {
        this(0L, "");
    }

    public DeviceTemplate(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
