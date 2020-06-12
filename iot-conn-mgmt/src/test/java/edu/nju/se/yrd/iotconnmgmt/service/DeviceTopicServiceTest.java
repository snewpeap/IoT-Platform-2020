package edu.nju.se.yrd.iotconnmgmt.service;

import com.github.javafaker.Faker;
import edu.nju.se.yrd.iotconnmgmt.entity.*;
import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;
import edu.nju.se.yrd.iotconnmgmt.repository.DeviceTopicRepository;
import edu.nju.se.yrd.iotconnmgmt.repository.ProtocolRepository;
import edu.nju.se.yrd.iotconnmgmt.serviceImpl.DeviceTopicServiceImpl;
import edu.nju.se.yrd.iotconnmgmt.util.TopicTool;
import edu.nju.se.yrd.iotconnmgmt.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceTopicServiceTest {

    Faker faker = new Faker();

    DeviceTopicServiceImpl service;
    DeviceTopicRepository deviceTopicRepository;
    ProtocolRepository protocolRepository;

    final Protocol mqtt = new Protocol();
    final DeviceTemplate validDeviceTemplate = new DeviceTemplate(UUID.randomUUID().toString(), "devTemplate");
    final Device validDevice = new Device(UUID.randomUUID().toString(), "dev", validDeviceTemplate);
    final DeviceTemplateTopic validTemplateTopic = new DeviceTemplateTopic();
    List<String> validName = Arrays.asList("/get/error", "/+/error");

    @BeforeEach
    void setUp() {
        mqtt.setName("MQTT");
        mqtt.setImplement(IProtocol.class);
        mqtt.setJarFile("mqtt.jar");

        validTemplateTopic.setId(1L);
        validTemplateTopic.setHost(validDeviceTemplate);
        validTemplateTopic.setName(String.format("/%s/%s", faker.hacker().verb().replaceAll(" ", ""), faker.hacker().noun()));
        validTemplateTopic.setDescription(faker.shakespeare().hamletQuote());
        validTemplateTopic.setInbound(true);
        validTemplateTopic.setOutbound(false);
        validTemplateTopic.setProtocol(mqtt);

        DeviceTopic dt = new DeviceTopic();
        dt.setParent(validTemplateTopic);
        dt.setHost(validDevice);

        deviceTopicRepository = mock(DeviceTopicRepository.class);
        when(deviceTopicRepository.existsByName(anyString())).thenReturn(false);
        when(deviceTopicRepository.getFirstByHost_Id(eq(validDevice.getId()))).thenReturn(Optional.of(dt));
        protocolRepository = mock(ProtocolRepository.class);
        when(protocolRepository.findByName(mqtt.getName())).thenReturn(Optional.of(mqtt));
        when(protocolRepository.findByName(argThat(argument -> !argument.equals(mqtt.getName())))).thenReturn(Optional.empty());

        service = new DeviceTopicServiceImpl(deviceTopicRepository, protocolRepository);
    }

    @Test
    void getTopics_valid() {
        int n = 10;
        when(deviceTopicRepository.getByHost_Id(validDevice.getId())).thenReturn(generateN(n));

        CarryPayloadResponse<List<DeviceTopicVO>> response = service.getTopics(validDevice.getId());
        assertTrue(response.getSuccess());
        assertEquals(n, response.getPayload().size());
        response.getPayload().forEach(vo -> {
            assertEquals(validDevice.getName(), vo.getDeviceName());
            System.out.println(vo);
        });

        String notExist = UUID.randomUUID().toString();
        when(deviceTopicRepository.getByHost_Id(notExist)).thenReturn(Collections.emptyList());
        response = service.getTopics(notExist);
        assertTrue(response.getSuccess());
        assertEquals(0, response.getPayload().size());
    }

    @Test
    void getTopics_invalid() {
        assertThrows(IllegalArgumentException.class, () -> service.getTopics(null));
    }

    @Test
    void addTopic_valid() {
        when(deviceTopicRepository.save(any(DeviceTopic.class))).thenReturn(new DeviceTopic());

        DeviceTopicForm form = new DeviceTopicForm();
        form.setDeviceId(validDevice.getId());
        form.setName(validName.get(0));
        form.setDescription(faker.shakespeare().hamletQuote());
        form.setOutbound(validTemplateTopic.getOutbound());
        form.setInbound(validTemplateTopic.getInbound());
        form.setProtocol(mqtt.getName());
        BasicResponse response = service.addTopic(form);
        assertTrue(response.getSuccess());

        form.setName(validName.get(1));
        response = service.addTopic(form);
        assertTrue(response.getSuccess());
    }

    @Test
    void addTopic_invalid() {
        when(deviceTopicRepository.save(any(DeviceTopic.class))).thenReturn(new DeviceTopic());

        //No device id
        DeviceTopicForm form = new DeviceTopicForm();
        form.setName(validName.get(0));
        form.setDescription(faker.shakespeare().hamletQuote());
        form.setOutbound(false);
        form.setInbound(true);
        form.setProtocol(mqtt.getName());
        BasicResponse response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //wildcard for outbound+inbound
        form.setDeviceId(validDevice.getId());
        form.setOutbound(true);
        form.setName(validName.get(1));
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //wildcard for outbound
        form.setInbound(false);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //neither inbound nor outbound
        form.setName(validName.get(0));
        form.setOutbound(false);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        form.setInbound(true);

        //invalid protocol
        form.setName(validName.get(0));
        form.setProtocol("HTTP");
        response = service.addTopic(form);
        assertFalse(response.getSuccess());
        form.setProtocol(null);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        form.setProtocol(mqtt.getName());

        //duplicate topic
        String duplicate = "/is/duplicate";
        when(deviceTopicRepository
                .existsByName(eq(TopicTool.generateName(validDeviceTemplate.getId(), validDevice.getId(), duplicate))))
                .thenReturn(true);
        form.setName(duplicate);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());

        //Not exist device
        String invalidDeviceId = UUID.randomUUID().toString();
        when(deviceTopicRepository.getFirstByHost_Id(invalidDeviceId)).thenReturn(Optional.empty());
        form.setName(validName.get(0));
        form.setDeviceId(invalidDeviceId);
        response = service.addTopic(form);
        assertFalse(response.getSuccess());
    }

    @Test
    void removeTopic() {
    }

    @Test
    void sendMessage() {
    }

    List<DeviceTopic> generateN(int n) {
        List<DeviceTopic> list = new ArrayList<>(n);
        while (list.size() != n) {
            DeviceTemplateTopic dtt = new DeviceTemplateTopic();
            dtt.setId((long) (list.size() + 1));
            dtt.setHost(validDeviceTemplate);
            dtt.setName(TopicTool.generateNameFromUserInput(String.format("/%s/%s", faker.hacker().verb().replaceAll(" ", ""), faker.hacker().noun()), validDeviceTemplate.getId()));
            dtt.setDescription(faker.shakespeare().hamletQuote());
            dtt.setOutbound(faker.bool().bool());
            dtt.setInbound(faker.bool().bool());
            dtt.setProtocol(mqtt);

            DeviceTopic dt = new DeviceTopic();
            dt.setId((long) (list.size() + 1));
            dt.setHost(validDevice);
            dt.setParent(dtt);
            dt.setName(TopicTool.generateTopicFromTemplate(validDevice.getId(), dtt.getName()));
            dt.setDescription(faker.shakespeare().hamletQuote());
            dt.setOutbound(dtt.getOutbound());
            dt.setInbound(dtt.getInbound());
            dt.setProtocol(dtt.getProtocol());
            list.add(dt);
        }
        return list;
    }
}