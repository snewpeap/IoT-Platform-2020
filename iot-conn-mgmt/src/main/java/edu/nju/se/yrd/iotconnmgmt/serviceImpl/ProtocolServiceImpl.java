package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import edu.nju.se.yrd.iotconnmgmt.entity.Protocol;
import edu.nju.se.yrd.iotconnmgmt.repository.ProtocolRepository;
import edu.nju.se.yrd.iotconnmgmt.service.ProtocolService;
import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProtocolServiceImpl implements ProtocolService {
    private final ProtocolRepository protocolRepository;

    public ProtocolServiceImpl(ProtocolRepository protocolRepository) {
        this.protocolRepository = protocolRepository;
    }

    @Override
    @Cacheable(value = "getProtocols")
    public CarryPayloadResponse<List<String>> getProtocols() {
        return CarryPayloadResponse.ok(protocolRepository.findAll().stream().map(Protocol::getName).collect(Collectors.toList()));
    }

    @Override
    public BasicResponse newProtocol(File file) {
        return null;
    }
}
