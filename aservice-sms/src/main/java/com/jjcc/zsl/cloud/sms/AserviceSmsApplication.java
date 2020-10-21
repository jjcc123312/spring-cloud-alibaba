package com.jjcc.zsl.cloud.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AserviceSmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AserviceSmsApplication.class, args);
	}

}
