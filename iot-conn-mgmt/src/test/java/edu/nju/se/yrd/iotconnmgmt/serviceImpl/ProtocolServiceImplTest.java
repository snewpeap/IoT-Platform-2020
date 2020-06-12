package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import edu.nju.se.yrd.iotconnmgmt.entity.Protocol;
import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;
import edu.nju.se.yrd.iotconnmgmt.repository.ProtocolRepository;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProtocolServiceImplTest {
    ProtocolServiceImpl service;
    ProtocolRepository protocolRepository;
    List<Protocol> protocols;

    @BeforeEach
    void setUp() {
        protocolRepository = mock(ProtocolRepository.class);

        Protocol mqtt = new Protocol(), http = new Protocol();
        mqtt.setName("MQTT");   mqtt.setImplement(IProtocol.class); mqtt.setJarFile("mqtt.jar");
        http.setName("HTTP");   http.setImplement(IProtocol.class); http.setJarFile("http.jar");
        protocols = Arrays.asList(mqtt, http);

        when(protocolRepository.findAll()).thenReturn(protocols);

        service = new ProtocolServiceImpl(protocolRepository);
    }

    @Test
    void getProtocols() {
        CarryPayloadResponse<List<String>> response = service.getProtocols();
        assertTrue(response.getSuccess());
        assertLinesMatch(protocols.stream().map(Protocol::getName).collect(Collectors.toList()), response.getPayload());
    }

    @Test
    void newProtocol() {
    }
}