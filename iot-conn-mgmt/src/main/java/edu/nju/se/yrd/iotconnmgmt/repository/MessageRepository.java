package edu.nju.se.yrd.iotconnmgmt.repository;

import edu.nju.se.yrd.iotconnmgmt.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, String> {
    /**
     * 获得某个Topic的所有消息
     *
     * @param topicId Topic的Id
     * @return Topic的消息
     */
    List<Message> getByTopic_Id(Long topicId);
}
