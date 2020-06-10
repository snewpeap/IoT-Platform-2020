package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import edu.nju.se.yrd.iotconnmgmt.service.ProtocolService;
import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;

import java.io.File;
import java.util.List;

public class ProtocolServiceImpl implements ProtocolService {
    @Override
    public CarryPayloadResponse<List<String>> getProtocols() {
        return null;
    }

    @Override
    public BasicResponse newProtocol(File file) {
        return null;
    }
}
