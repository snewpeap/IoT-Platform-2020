package edu.nju.se.yrd.iotconnmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IotConnMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotConnMgmtApplication.class, args);
    }

}
