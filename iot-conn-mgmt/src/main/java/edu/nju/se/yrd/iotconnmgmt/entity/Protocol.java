package edu.nju.se.yrd.iotconnmgmt.entity;

import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;

@Entity
public class Protocol {
    @Id
    private String name;
    private Class<IProtocol> implement;
    private URL jarFile;

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

    public URL getJarFile() {
        return jarFile;
    }

    public void setJarFile(URL jarFile) {
        this.jarFile = jarFile;
    }
}
