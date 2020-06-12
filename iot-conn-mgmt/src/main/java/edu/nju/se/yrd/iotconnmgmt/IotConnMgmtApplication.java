package edu.nju.se.yrd.iotconnmgmt;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableSwagger2Doc
public class IotConnMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotConnMgmtApplication.class, args);
    }

}
