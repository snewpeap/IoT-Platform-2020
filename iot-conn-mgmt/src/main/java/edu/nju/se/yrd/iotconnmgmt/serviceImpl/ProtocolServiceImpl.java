package edu.nju.se.yrd.iotconnmgmt.serviceImpl;

import edu.nju.se.yrd.iotconnmgmt.entity.Protocol;
import edu.nju.se.yrd.iotconnmgmt.protocol.IProtocol;
import edu.nju.se.yrd.iotconnmgmt.protocolImpl.ProtocolMQTTImpl;
import edu.nju.se.yrd.iotconnmgmt.repository.ProtocolRepository;
import edu.nju.se.yrd.iotconnmgmt.service.DeviceTopicService;
import edu.nju.se.yrd.iotconnmgmt.service.ProtocolService;
import edu.nju.se.yrd.iotconnmgmt.util.SimpleClassLoader;
import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import edu.nju.se.yrd.iotconnmgmt.vo.CarryPayloadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

@Service
public class ProtocolServiceImpl implements ProtocolService {
    private static final ConcurrentHashMap<String, IProtocol> protocolMap = new ConcurrentHashMap<>();
    private final ProtocolRepository protocolRepository;

    @Autowired
    public ProtocolServiceImpl(ProtocolRepository protocolRepository) {
        this.protocolRepository = protocolRepository;
    }

    @PostConstruct
    void postConstruct() {
        Protocol mqtt = new Protocol();
        mqtt.setName("MQTT");
        mqtt.setImplement(ProtocolMQTTImpl.class.getName());
        mqtt.setJarFile("");
        protocolRepository.saveAndFlush(mqtt);

        protocolRepository.findAll().forEach(protocol -> {
            try {
                if (protocol.getName().equals("MQTT")) {
                    protocolMap.put("MQTT", new ProtocolMQTTImpl());
                } else {
                    JarFile jarFile = new JarFile(new File(protocol.getJarFile()));
                    Class<IProtocol> iProtocolClass = getProtocol(jarFile, protocol.getImplement());
                    protocolMap.put(protocol.getName(), iProtocolClass.newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void register(DeviceTopicService deviceTopicService) {
        protocolMap.forEachValue(1, protocol -> protocol.startListen(deviceTopicService));
    }

    @Override
    public IProtocol getProtocolInstance(String name) {
        return protocolMap.get(name);
    }

    @Override
    public CarryPayloadResponse<List<String>> getProtocols() {
        return CarryPayloadResponse.ok(protocolRepository.findAll().stream().map(Protocol::getName).collect(Collectors.toList()));
    }

    @Override
    public BasicResponse newProtocol(File file) {
        JarFile uploadFile;
        try {
            uploadFile = new JarFile(file);
        } catch (IOException e) {
            return BasicResponse.error().message("不是一个有效的jar文件");
        }
        Manifest manifest;
        try {
            manifest = uploadFile.getManifest();
        } catch (IOException e) {
            return BasicResponse.error().message("读取manifest文件时出现错误，请检查manifest文件正确性");
        }
        String targetClassName = manifest.getAttributes("Protocol-Implement-Class").getValue("Protocol-Implement-Class");
        Assert.notNull(targetClassName, "未在manifest中定义Protocol-Implement-Class属性");

        Class<IProtocol> protocolClass;
        try {
            protocolClass = getProtocol(uploadFile, targetClassName);
        } catch (IOException ioException) {
            return BasicResponse.error().message("读取文件异常");
        } catch (Exception e) {
            return BasicResponse.error().message(e.getMessage());
        }
        Assert.notNull(protocolClass, targetClassName + "未实现接口IProtocol");
        IProtocol protocol;
        try {
            protocol = protocolClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return BasicResponse.error().message("服务器异常");
        }
        String protocolName = protocol.getProtocolName();
        Assert.notNull(protocolName, "协议名不能为空");
        IProtocol iProtocol = protocolMap.get(protocolName);
        if (iProtocol != null) {
            return BasicResponse.error().message(protocolName + "已经有实现类" + iProtocol.getClass().getName());
        }
        Protocol newProtocol = new Protocol();
        newProtocol.setName(protocolName);
        newProtocol.setImplement(targetClassName);
        newProtocol.setJarFile(uploadFile.getName());
        protocolRepository.save(newProtocol);
        protocolMap.put(protocolName, protocol);

        return BasicResponse.ok();
    }

    private Class<IProtocol> getProtocol(JarFile jarFile, String className) throws Exception {
        Enumeration<JarEntry> entries = jarFile.entries();
        InputStream classInputStream = null;
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();
            if (!entry.isDirectory() && entryName.endsWith(".class")
                    && className.equals(entryName.replace(".class", "").replace('/', '.'))) {
                classInputStream = jarFile.getInputStream(entry);
            }
        }
        if (classInputStream == null) {
            throw new Exception("未在jar中找到类" + className);
        }
        SimpleClassLoader classLoader = new SimpleClassLoader();
        Class<?> clazz = classLoader.loadClass(classInputStream, className);
        Class<IProtocol> protocolClass = null;
        for (Class<?> interfaces : clazz.getInterfaces()) {
            if (interfaces.equals(IProtocol.class)) {
                protocolClass = (Class<IProtocol>) clazz;
                break;
            }
        }
        return protocolClass;
    }
}
