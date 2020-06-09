package edu.nju.se.yrd.iotconnmgmt.controller;

import edu.nju.se.yrd.iotconnmgmt.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/topic")
public class ApiController {

    @GetMapping("/template/{deviceTemplateId}")
    public CarryPayloadResponse<List<DeviceTemplateTopicVO>> getDeviceTemplateTopics(@PathVariable Long deviceTemplateId) {
        return null;
    }

    @PostMapping("/template")
    public BasicResponse addDeviceTemplateTopic(@RequestBody DeviceTemplateTopicForm form) {
        return null;
    }

    @DeleteMapping("/template/{topicId}")
    public BasicResponse removeDeviceTemplateTopic(@PathVariable Long topicId) {
        return null;
    }

    @GetMapping("/device/{deviceId}")
    public CarryPayloadResponse<List<DeviceTopicVO>> getDeviceTopics(@PathVariable Long deviceId) {
        return null;
    }

    @PostMapping("/device")
    public BasicResponse addDeviceTopic(@RequestBody DeviceTopicForm form) {
        return null;
    }

    @DeleteMapping("/device/{topicId}")
    public BasicResponse removeDeviceTopic(@PathVariable Long topicId) {
        return null;
    }

    @PostMapping("/{topicId}/message")
    public CarryPayloadResponse<String> manuallySendMessage(@PathVariable Long topicId, @RequestParam Boolean async, @RequestBody String message) {
        return null;
    }

    @GetMapping("/{topicId}/message")
    public CarryPayloadResponse<List<MessageVO>> getMessages(@PathVariable Long topicId) {
        return null;
    }

    //TODO SDK上传
}
