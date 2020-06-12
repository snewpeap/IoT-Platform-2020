package edu.nju.se.yrd.iotconnmgmt.service;

import com.github.javafaker.Faker;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplate;
import edu.nju.se.yrd.iotconnmgmt.entity.DeviceTemplateTopic;
import edu.nju.se.yrd.iotconnmgmt.entity.Protocol;
import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;
import edu.nju.se.yrd.iotconnmgmt.repository.DeviceTemplateTopicRepository;
import edu.nju.se.yrd.iotconnmgmt.repository.ProtocolRepository;
import edu.nju.se.yrd.iotconnmgmt.serviceImpl.DeviceTemplateTopicServiceImpl;
import edu.nju.se.yrd.iotconnmgmt.util.TopicTool;
import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicForm;
import edu.nju.se.yrd.iotconnmgmt.vo.DeviceTemplateTopicVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceTemplateTopicServiceTest {

    Faker faker = new Faker();

    DeviceTemplateTopicServiceImpl service;
    DeviceTemplateTopicRepository templateTopicRepository;
    ProtocolRepository protocolRepository;

    final DeviceTemplate validDeviceTemplate = new DeviceTemplate(UUID.randomUUID().toString(), "valid");
    final String nonExistDeviceTemplateId = UUID.randomUUID().toString();
    final Protocol mqtt = new Protocol();

    @BeforeEach
    void setUp() {
        mqtt.setName("MQTT");
        mqtt.setImplement(IProtocol.class);
        mqtt.setJarFile("mqtt.jar");

        templateTopicRepository = mock(DeviceTemplateTopicRepository.class);
        when(templateTopicRepository.existsByName(anyString())).thenReturn(false);
        protocolRepository = mock(ProtocolRepository.class);
        when(protocolRepository.findByName(mqtt.getName())).thenReturn(Optional.of(mqtt));
        when(protocolRepository.findByName(argThat(argument -> !argument.equals(mqtt.getName())))).thenReturn(Optional.empty());

        service = new DeviceTemplateTopicServiceImpl(templateTopicRepository, protocolRepository);
    }

    @Test
    void getTopics_valid() {
        int n = 10;
        when(templateTopicRepository.getByHost_Id(validDeviceTemplate.getId())).thenReturn(generateN(n));

        CarryPayloadResponse<List<DeviceTemplateTopicVO>> result = service.getTopics(validDeviceTemplate.getId());
        assertTrue(result.getSuccess());
        assertEquals(n, result.getPayload().size());
        result.getPayload().forEach(vo -> {
            assertEquals(validDeviceTemplate.getName(), vo.getDeviceTemplateName());
            System.out.println(vo);
        });

        when(templateTopicRepository.getByHost_Id(nonExistDeviceTemplateId)).thenReturn(Collections.emptyList());
        result = service.getTopics(nonExistDeviceTemplateId);
        assertTrue(result.getSuccess());
        assertEquals(0, result.getPayload().size());
    }

    @Test
    void getTopics_invalid() {
        //null value argument
        assertThrows(IllegalArgumentException.class, () -> service.getTopics(null));
    }

    @Test
    void addTopic_valid() {
        when(templateTopicRepository.save(any(DeviceTemplateTopic.class))).thenReturn(new DeviceTemplateTopic());

        DeviceTemplateTopicForm form = new DeviceTemplateTopicForm();
        form.setDeviceTemplateId(validDeviceTemplate.getId());
        form.setName("/get/error");
        form.setDescription(faker.shakespeare().hamletQuote());
        form.setOutbound(false);
        form.setInbound(true);
        form.setProtocol(mqtt.getName());
        BasicResponse response = service.addTopic(form);
        assertTrue(response.getSuccess());

        form.setName("/+/error");
        response = service.addTopic(form);
        assertTrue(response.getSuccess());

        form.setName("/get/#");
        response = service.addTopic(form);
        assertTrue(response.getSuccess());

        form.setName("/get/error");
        form.setOutbound(true);
        response = service.addTopic(form);
        assertTrue(response.getSuccess());
    }

    @Test
    void addTopic_invalid() {
        when(templateTopicRepository.save(any(DeviceTemplateTopic.class))).thenReturn(new DeviceTemplateTopic());

        //No device template id
        DeviceTemplateTopicForm form = new DeviceTemplateTopicForm();
        form.setName("/get/error");
        form.setDescription(faker.shakespeare().hamletQuote());
        form.setOutbound(false);
        form.setInbound(true);
        form.setProtocol(mqtt.getName());
        BasicResponse response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //wildcard for outbound+inbound
        form.setDeviceTemplateId(validDeviceTemplate.getId());
        form.setOutbound(true);
        form.setName("/+/error");
        response = service.addTopic(form);
        assertFalse(response.getSuccess());
        form.setName("/get/#");
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //wildcard for outbound
        form.setInbound(false);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //neither inbound nor outbound
        form.setName("/get/error");
        form.setOutbound(false);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //illegal char
        form.setInbound(true);
        form.setName("/get?/error!");
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //invalid protocol
        form.setName("/get/error");
        form.setProtocol("HTTP");
        response = service.addTopic(form);
        assertFalse(response.getSuccess());
        form.setProtocol(null);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        form.setProtocol(mqtt.getName());

        //duplicate
        String duplicate = "/is/duplicate";
        when(templateTopicRepository
                .existsByName(eq(TopicTool.generateNameFromUserInput(duplicate, form.getDeviceTemplateId()))))
                .thenReturn(true);
        form.setName(duplicate);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //Not exist device template
        String invalidDeviceTemplateId = UUID.randomUUID().toString();
        when(templateTopicRepository.getFirstByHost_Id(invalidDeviceTemplateId)).thenReturn(Optional.empty());
        form.setName("/get/error");
        form.setDeviceTemplateId(invalidDeviceTemplateId);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());
    }

    @Test
    void removeTopic_invalid() {
    }

    List<DeviceTemplateTopic> generateN(int n) {
        List<DeviceTemplateTopic> list = new ArrayList<>(n);
        while (list.size() != n) {
            DeviceTemplateTopic dtt = new DeviceTemplateTopic();
            dtt.setId((long) (list.size() + 1));
            dtt.setHost(validDeviceTemplate);
            dtt.setName(TopicTool.generateNameFromUserInput(String.format("/%s/%s", faker.hacker().verb(), faker.hacker().noun()), validDeviceTemplate.getId()));
            dtt.setDescription(faker.shakespeare().hamletQuote());
            dtt.setOutbound(faker.bool().bool());
            dtt.setInbound(faker.bool().bool());
            dtt.setProtocol(mqtt);
            list.add(dtt);
        }
        return list;
    }
}