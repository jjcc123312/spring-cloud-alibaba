package com.jjcc.zsl.cloud.rabc.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 黑白名单限制
 * @className: WhiteAndBlockListController.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-12-17 20:15
 */
@RestController
@RequestMapping("api/whiteBlockList")
public class WhiteAndBlockListController {


    @GetMapping(value = "/testWhite")
    @SentinelResource(value = "testWhiteResource")
    public String testWhite() {
        System.out.println(112);
        return "1112";
    }

}
