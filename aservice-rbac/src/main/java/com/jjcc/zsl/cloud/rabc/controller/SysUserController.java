package com.jjcc.zsl.cloud.rabc.controller;

import com.jjcc.zsl.cloud.rabc.feign.SmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: SysuserController.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-09-25 17:51
 */
@RestController
@RequestMapping("/api/user")
public class SysUserController {

    private final SmsService smsService;

    public SysUserController(SmsService smsService) {
        this.smsService = smsService;
    }

    @GetMapping(value = "/pwd/reset")
    public ResponseEntity<Object> pwdreset(@RequestParam Integer userId) {
//        sysuserService.pwdreset(userId);
        String val = smsService.send("123", "更换密码");
        return new ResponseEntity<>(val, HttpStatus.OK);
    }
}
