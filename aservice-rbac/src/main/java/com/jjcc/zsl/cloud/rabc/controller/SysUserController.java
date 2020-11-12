package com.jjcc.zsl.cloud.rabc.controller;

import com.jjcc.zsl.cloud.rabc.domain.OrderProperties;
import com.jjcc.zsl.cloud.rabc.feign.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class SysUserController {

    @Value(value = "${order.pay-timeout-seconds}") // @NacosValue(value = "${order.pay-timeout-seconds}")
    private Integer payTimeoutSeconds;
    @Value(value = "${order.create-frequency-seconds}") // @NacosValue(value = "${order.create-frequency-seconds}")
    private Integer createFrequencySeconds;


    private final SmsService smsService;

    private final OrderProperties orderProperties;

    public SysUserController(SmsService smsService, OrderProperties orderProperties) {
        this.smsService = smsService;
        this.orderProperties = orderProperties;
    }



    @GetMapping(value = "/pwd/reset")
    public ResponseEntity<Object> pwdreset(@RequestParam Integer userId) {
//        sysuserService.pwdreset(userId);
        String val = smsService.send("123", "更换密码");
        String a = "payTimeoutSeconds: " + payTimeoutSeconds + "；createFrequencySeconds："
                + createFrequencySeconds;
        return new ResponseEntity<>(val, HttpStatus.OK);
    }
}
