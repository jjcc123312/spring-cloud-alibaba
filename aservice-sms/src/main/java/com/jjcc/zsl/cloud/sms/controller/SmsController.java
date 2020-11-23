package com.jjcc.zsl.cloud.sms.controller;

import com.jjcc.zsl.cloud.sms.service.dto.DemoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @className: SmsController.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-09-25 17:56
 */
@RestController
@RequestMapping("/api/sms")
@RefreshScope
public class SmsController {

    @Value("${server.port}")
    private Integer serverPort;

    @PostMapping(value = "/sms/send")
    public String send(@RequestParam("phoneNo") String phoneNo,
                                @RequestParam("content") String content){
        String str = phoneNo + content + "。port：" + serverPort;
        return str;
    }

    @GetMapping("/get_demo")
    public ResponseEntity<Object> getDemo(DemoDTO demoDTO) {
        System.out.println(123);
        int i = 1 / 0;
        return new ResponseEntity<>(demoDTO, HttpStatus.OK);
    }

    @PostMapping("/post_demo")
    public DemoDTO postDemo(@RequestBody DemoDTO demoDTO) {
        return demoDTO;
    }

    @PostMapping("/get_demo_querMapp")
    public DemoDTO getDemoQuerMapp(DemoDTO demoDTO) {
        return demoDTO;
    }
}






