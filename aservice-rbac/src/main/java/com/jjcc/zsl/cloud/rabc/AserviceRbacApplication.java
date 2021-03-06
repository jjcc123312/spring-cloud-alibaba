package com.jjcc.zsl.cloud.rabc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(basePackages = {"com.jjcc.zsl.cloud.**.mapper"})
@EnableFeignClients
public class AserviceRbacApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(AserviceRbacApplication.class, args);
    }

}
