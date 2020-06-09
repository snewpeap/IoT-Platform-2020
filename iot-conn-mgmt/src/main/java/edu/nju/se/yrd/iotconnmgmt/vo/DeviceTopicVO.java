package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.Device;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTopic;

public class DeviceTopicVO extends DeviceTopic {
    public DeviceTopicVO(Device host, String name, String description, Boolean upstream, Boolean downstream) {
        super(host, name, description, upstream, downstream);
    }

    public DeviceTopicVO() {
        super();
    }
}
