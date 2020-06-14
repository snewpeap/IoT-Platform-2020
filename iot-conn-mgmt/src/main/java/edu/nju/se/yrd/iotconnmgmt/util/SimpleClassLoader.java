package edu.nju.se.yrd.iotconnmgmt.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleClassLoader extends ClassLoader {
    public Class<?> loadClass(InputStream classInputStream, String name) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = classInputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
        baos.close();
        return clazz;
    }
}
