package com.jjcc.zsl.cloud.rabc.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 热点参数限流
 * @className: FlowLimitController.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-12-16 20:25
 */
@RestController
@RequestMapping("api/flowLimit")
public class FlowLimitController {


    @GetMapping(value = "/testHotKey")
    //通过blockHandler指定熔断降级的方法，通过fallback指定触发异常执行的降级方法
    @SentinelResource(value = "testHotKeyResource")
    public String testHotKey(
            @RequestParam(value = "p1", required = false) String p1,
            @RequestParam(value = "p2", required = false) String p2) {
        System.out.println(112);
        return "testHotKey" + p1 + "；" + p2;
    }
}
