package com.jjcc.zsl.cloud.sms.controller;

import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value = "/sms/send")
    public String send(@RequestParam("phoneNo") String phoneNo,
                                @RequestParam("content") String content){
        String str = phoneNo + content + "!!!!!!!";
        return str;
    }
}
