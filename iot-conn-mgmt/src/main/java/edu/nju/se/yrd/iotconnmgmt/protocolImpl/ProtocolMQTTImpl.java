package edu.nju.se.yrd.iotconnmgmt.protocolImpl;

import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Observable;
import java.util.Observer;

public class ProtocolMQTTImpl extends Observable implements IProtocol {
    public static final String HOST = "tcp://127.0.0.1:1883";
    public static final String CLIENT_ID = "platform";
    private final MqttClient mqttClient;

    public ProtocolMQTTImpl() throws MqttException {
        mqttClient = new MqttClient(HOST, CLIENT_ID, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setConnectionTimeout(60);
        options.setKeepAliveInterval(180);
        options.setAutomaticReconnect(true);
        options.setWill("close", "offline".getBytes(), 0, true);
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.err.println("连接断开");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                String[] msg = new String[]{topic, new String(message.getPayload())};
                setChanged();
                notifyObservers(msg);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("发布完成");
            }
        });
        mqttClient.connect();
        System.out.println("MQTT连接成功");
    }

    @Override
    public String getProtocolName() {
        return "MQTT";
    }

    @Override
    public void startListen(Observer observer) {
        addObserver(observer);
    }

    @Override
    public boolean send(String topic, String message) throws Exception {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(0);
        mqttMessage.setPayload(message.getBytes());
        mqttClient.publish(topic, mqttMessage);
        return true;
    }

    @Override
    public boolean subscribe(String topic) throws Exception {
        mqttClient.subscribe(topic, 0);
        return true;
    }
}
