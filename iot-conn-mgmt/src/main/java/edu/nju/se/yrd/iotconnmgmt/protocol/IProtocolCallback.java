package edu.nju.se.yrd.iotconnmgmt.protocol;

public interface IProtocolCallback {
    boolean receive(String topic, String message);
}
