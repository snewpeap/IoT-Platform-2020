package edu.nju.se.yrd.iotconnmgmt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Protocol {
    @Id
    private String name;
    private String implement;
    private String jarFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImplement() {
        return implement;
    }

    public void setImplement(String implement) {
        this.implement = implement;
    }

    public String getJarFile() {
        return jarFile;
    }

    public void setJarFile(String jarFile) {
        this.jarFile = jarFile;
    }
}
