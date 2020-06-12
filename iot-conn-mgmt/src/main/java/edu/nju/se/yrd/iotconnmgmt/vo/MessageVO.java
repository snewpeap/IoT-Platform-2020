package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.Message;

import java.util.Date;
import java.util.StringJoiner;

public class MessageVO {
    private String id;
    private Date time;
    private String content;
    private String direction;
    private String status;

    public MessageVO() {
    }

    public MessageVO(String id, Date time, String content, String direction, String status) {
        this.id = id;
        this.time = time;
        this.content = content;
        this.direction = direction;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MessageVO.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("time=" + time)
                .add("content='" + content + "'")
                .add("direction='" + direction + "'")
                .add("status='" + status + "'")
                .toString();
    }

    public static MessageVO convertFromEntity(Message entity) {
        return new MessageVO(
                entity.getId(),
                new Date(entity.getTimestamp()),
                entity.getContent(),
                entity.getDirection().name(),
                entity.getStatus().name());
    }
}
