package edu.nju.se.yrd.iotconnmgmt.protocol;

public interface IProtocol {
    String getProtocolName();

    IProtocolCallback getCallback();

    boolean send(String topic, String message);
}
