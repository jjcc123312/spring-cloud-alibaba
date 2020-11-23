package com.jjcc.zsl.cloud.rabc.feign.impl;

import com.jjcc.zsl.cloud.rabc.feign.SmsService;
import com.jjcc.zsl.cloud.rabc.service.dto.DemoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @className: SmsServiceImpl.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-11-19 19:23
 */
@Component
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String phoneNo, String content) {
        return "请求接口失败";
    }

    @Override
    public ResponseEntity<Object> getDemo(DemoDTO demoDTO) {
        System.out.println(123);
        DemoDTO demoDTO1 = new DemoDTO();
        return new ResponseEntity<>("接口有问题！！！", HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public DemoDTO getDemoQuerMapp(DemoDTO demoDTO) {
        throw new RuntimeException("请求接口失败");
    }

    @Override
    public DemoDTO postDemo(DemoDTO demoDTO) {
        throw new RuntimeException("请求接口失败");
    }
}
