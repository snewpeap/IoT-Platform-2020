package edu.nju.se.yrd.iotconnmgmt.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Device {
    private Long id;
    private String name;
    private boolean online;

    public Device() {
        this(0L, "");
    }

    public Device(Long id, String name) {
        this.id = id;
        this.name = name;
        online = false;
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

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
