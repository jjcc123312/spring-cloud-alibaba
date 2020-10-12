package com.jjcc.zsl.cloud.rabc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign远程调用类
 * @className: SmsService.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-09-25 17:47
 */
@FeignClient(value = "ASERVICE-SMS", path = "/api/sms")
public interface SmsService {

    @PostMapping(value = "/sms/send")
    String send(@RequestParam("phoneNo") String phoneNo,
                                @RequestParam("content") String content);
}
