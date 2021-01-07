package com.jjcc.zsl.cloud.sms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: SmsController.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-09-25 17:56
 */
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @GetMapping(value = "/sms/send")
    public String send(@RequestParam("phoneNo") String phoneNo,
                                @RequestParam("content") String content){
        String str = phoneNo + content + "!!!!!!!";
        return str;
    }

    @GetMapping(value = "/sms/echo")
    public ResponseEntity<String> echo() {
        System.out.println("!!!!");
        int i = 1 / 0;
        return new ResponseEntity<>("成功！！！", HttpStatus.OK);
    }
}
