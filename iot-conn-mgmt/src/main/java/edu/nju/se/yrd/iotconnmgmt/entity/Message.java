package edu.nju.se.yrd.iotconnmgmt.entity;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    private Long timestamp;
    private String content;
    @ManyToOne
    private DeviceTopic topic;
    @Enumerated
    private DIRECTION direction;
    @Enumerated
    private STATUS status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DeviceTopic getTopic() {
        return topic;
    }

    public void setTopic(DeviceTopic topic) {
        this.topic = topic;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public enum DIRECTION {
        UPSTREAM, DOWNSTREAM;
    }

    public enum STATUS {
        SENDING, SENT, FAILED;
    }
}
