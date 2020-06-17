package edu.nju.se.yrd.iotconnmgmt.controller;

import edu.nju.se.yrd.iotconnmgmt.service.DeviceTemplateTopicService;
import edu.nju.se.yrd.iotconnmgmt.service.DeviceTopicService;
import edu.nju.se.yrd.iotconnmgmt.service.ProtocolService;
import edu.nju.se.yrd.iotconnmgmt.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/topic")
public class ApiController {
    private final DeviceTemplateTopicService deviceTemplateTopicService;
    private final DeviceTopicService deviceTopicService;
    private final ProtocolService protocolService;

    @Autowired
    public ApiController(DeviceTemplateTopicService deviceTemplateTopicService,
                         DeviceTopicService deviceTopicService,
                         ProtocolService protocolService) {
        this.deviceTemplateTopicService = deviceTemplateTopicService;
        this.deviceTopicService = deviceTopicService;
        this.protocolService = protocolService;
    }

    @GetMapping("/template/{deviceTemplateId}")
    public CarryPayloadResponse<List<DeviceTemplateTopicVO>> getDeviceTemplateTopics(@PathVariable String deviceTemplateId) {
        return deviceTemplateTopicService.getTopics(deviceTemplateId);
    }

    @PostMapping("/template")
    public BasicResponse addDeviceTemplateTopic(@RequestBody DeviceTemplateTopicForm form) {
        return deviceTemplateTopicService.addTopic(form);
    }

//    @DeleteMapping("/template/{topicId}")
//    public BasicResponse removeDeviceTemplateTopic(@PathVariable Long topicId) {
//        return null;
//    }

    @GetMapping("/device/{deviceId}")
    public CarryPayloadResponse<List<DeviceTopicVO>> getDeviceTopics(@PathVariable String deviceId) {
        return deviceTopicService.getTopics(deviceId);
    }

    @PostMapping("/device")
    public BasicResponse addDeviceTopic(@RequestBody DeviceTopicForm form) {
        return deviceTopicService.addTopic(form);
    }

//    @DeleteMapping("/device/{topicId}")
//    public BasicResponse removeDeviceTopic(@PathVariable Long topicId) {
//        return null;
//    }

    @PostMapping("/device/{topicId}/message")
    public BasicResponse manuallySendMessage(@PathVariable Long topicId, @RequestBody String message) {
        return deviceTopicService.sendMessage(topicId, message);
    }

    @GetMapping("/device/{topicId}/message")
    public CarryPayloadResponse<List<MessageVO>> getMessages(@PathVariable Long topicId) {
        return deviceTopicService.getMessages(topicId);
    }

    @GetMapping("/protocol/detail")
    public CarryPayloadResponse<List<ProtocolVO>> getDetailProtocols() {
        return protocolService.getDetailProtocols();
    }

    @GetMapping("/protocol")
    public CarryPayloadResponse<List<String>> getProtocols() {
        return protocolService.getProtocols();
    }

    @PostMapping("/protocol")
    public BasicResponse uploadProtocol(@RequestParam MultipartFile multipartFile) {
        String storeTo = String.format("E:%sprotocols%s%s", File.separator, File.separator, multipartFile.getOriginalFilename());
        File file = new File(storeTo);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            return BasicResponse.error().message("文件上传失败");
        }
        return protocolService.newProtocol(file);
    }
}
