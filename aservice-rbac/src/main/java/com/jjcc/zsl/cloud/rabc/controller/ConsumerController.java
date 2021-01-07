package com.jjcc.zsl.cloud.rabc.controller;

import com.jjcc.zsl.cloud.rabc.feign.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ConsumerController.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2021-01-04 14:37
 */
@RestController
@RequestMapping("api/consumer")
public class ConsumerController {

    private final SmsService smsService;

    public ConsumerController(SmsService smsService) {
        this.smsService = smsService;
    }

    @GetMapping("/echo")
    public ResponseEntity<String> echo() {
        return smsService.echo();
    }

}
