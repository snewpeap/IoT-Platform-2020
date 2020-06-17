import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;

import java.util.Observable;
import java.util.Observer;

public class Random extends Observable implements IProtocol {
    @Override
    public String getProtocolName() {
        return "Random";
    }

    @Override
    public void startListen(Observer observer) {
        //do nothing
    }

    @Override
    public boolean send(String topic, String message) throws Exception {
        System.out.println("Random protocol get message \"" + message + "\" to " + topic);
        java.util.Random random = new java.util.Random();
        Thread.sleep(random.nextInt(5) * 1000);
        return random.nextBoolean();
    }

    @Override
    public boolean subscribe(String topic) throws Exception {
        return true;
    }
}
