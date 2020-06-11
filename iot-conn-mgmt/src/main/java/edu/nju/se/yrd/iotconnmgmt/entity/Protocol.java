package edu.nju.se.yrd.iotconnmgmt.entity;

import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Protocol {
    @Id
    private String name;
    private Class<IProtocol> implement;
    private String jarFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<IProtocol> getImplement() {
        return implement;
    }

    public void setImplement(Class<IProtocol> implement) {
        this.implement = implement;
    }

    public String getJarFile() {
        return jarFile;
    }

    public void setJarFile(String jarFile) {
        this.jarFile = jarFile;
    }
}
