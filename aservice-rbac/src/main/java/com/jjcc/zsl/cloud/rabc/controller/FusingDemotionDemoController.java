package com.jjcc.zsl.cloud.rabc.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jjcc.zsl.cloud.rabc.handler.SysUserControllerHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务熔断
 * @className: FusingDemotionDemoController.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-12-02 16:37
 */
@RestController
@RequestMapping(value = "/api/fusing")
public class FusingDemotionDemoController {

    /**
     * 慢调用比例
     * @title sleep
     * @author Jjcc
     * @return java.lang.String
     * @createTime 2020/12/4 17:04
     */
    @GetMapping("/sleep")
    public String sleep() throws InterruptedException {
        Thread.sleep(1000L);
        return "sleep";
    }

    /**
     * 异常比例
     * @title sleep
     * @author Jjcc
     * @return java.lang.String
     * @createTime 2020/12/4 17:04
     */
    @GetMapping("/error")
    @SentinelResource(value = "errorResource", blockHandlerClass = {SysUserControllerHandler.class}, blockHandler = "errorHandler")
    public ResponseEntity<Object> error(int a) {
        System.out.println(1123);
        int i = 10 / a;
        return new ResponseEntity<>("errpr", HttpStatus.OK);
    }

    /**
     * 异常数降级
     * @title sleep
     * @author Jjcc
     * @return java.lang.String
     * @createTime 2020/12/4 17:04
     */
    @GetMapping("/errorCount")
    @SentinelResource(value = "errorCountResource", blockHandlerClass = {SysUserControllerHandler.class},
            blockHandler = "errorHandler", fallback = "errorFallbackHandler")
    public ResponseEntity<Object> errorCount(int a) {
        System.out.println(1123);
        if (a == 0) {
//            int i = 10 / a;
//            throw new IllegalArgumentException("id 为 0");
            throw new NullPointerException("id 为 0");
        }
        return new ResponseEntity<>("errorCount", HttpStatus.OK);
    }

    public static ResponseEntity<Object> errorFallbackHandler(int a, Throwable blockException) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("message", "fallback:系统服务繁忙！请稍后再试！");
        return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    }


}
