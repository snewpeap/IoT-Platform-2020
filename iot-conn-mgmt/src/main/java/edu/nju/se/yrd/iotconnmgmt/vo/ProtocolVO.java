package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.Protocol;

import java.util.StringJoiner;

public class ProtocolVO {
    private String name;
    private String implement;
    private String location;

    public ProtocolVO() {
    }

    public ProtocolVO(String name, String implement, String location) {
        this.name = name;
        this.implement = implement;
        this.location = location;
    }

    public static ProtocolVO convertFromEntity(Protocol protocol) {
        return new ProtocolVO(
                protocol.getName(),
                protocol.getImplement(),
                protocol.getJarFile()
        );
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProtocolVO.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("implement='" + implement + "'")
                .add("location='" + location + "'")
                .toString();
    }
}
