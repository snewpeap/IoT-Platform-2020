package edu.nju.se.yrd.iotconnmgmt.vo;

import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplate;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplateTopic;

public class DeviceTemplateTopicVO extends DeviceTemplateTopic {
    public DeviceTemplateTopicVO(DeviceTemplate host, String name, String description, Boolean upstream, Boolean downstream) {
        super(host, name, description, upstream, downstream);
    }

    public DeviceTemplateTopicVO() {
        super();
    }
}
