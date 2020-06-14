package edu.nju.se.yrd.iotconnmgmt.protocol;

import java.util.Observer;

public interface IProtocol {
    String getProtocolName();

    void startListen(Observer observer);

    boolean send(String topic, String message) throws Exception;

    boolean subscribe(String topic) throws Exception;
}
