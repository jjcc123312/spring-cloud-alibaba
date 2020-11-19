package com.jjcc.zsl.cloud.rabc;

import com.jjcc.zsl.cloud.rabc.config.feignconfig.DefaultFeignClientConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(basePackages = {"com.jjcc.zsl.cloud.**.mapper"})
@EnableFeignClients(defaultConfiguration = DefaultFeignClientConfiguration.class)
@EnableDiscoveryClient
// 开启断路器功能
@EnableCircuitBreaker
public class AserviceRbacApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(AserviceRbacApplication.class, args);
    }

}
