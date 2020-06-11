package edu.nju.se.yrd.iotconnmgmt.entity;

import javax.persistence.Embeddable;

@Embeddable
public class DeviceTemplate {
    private String id;
    private String name;

    public DeviceTemplate() {
        this("", "");
    }

    public DeviceTemplate(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
