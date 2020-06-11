package edu.nju.se.yrd.iotconnmgmt.repository;

import edu.nju.se.yrd.iotconnmgmt.entity.Protocol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProtocolRepository extends JpaRepository<Protocol, Long> {
    /**
     * 根据作为主键的协议名称获取对应协议
     *
     * @param name 查询的协议名称
     * @return 查询结果
     */
    Optional<Protocol> findByName(String name);
}
