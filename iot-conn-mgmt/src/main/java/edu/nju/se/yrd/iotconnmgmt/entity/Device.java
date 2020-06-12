package edu.nju.se.yrd.iotconnmgmt.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Device {
    private String id;
    private String name;
    private DeviceTemplate template;

    public Device() {
        this("", "");
    }

    public Device(String id, String name) {
        this(id, name, new DeviceTemplate());
    }

    public Device(String id, String name, DeviceTemplate template) {
        this.id = id;
        this.name = name;
        this.template = template;
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

    public DeviceTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DeviceTemplate parent) {
        this.template = parent;
    }
}
