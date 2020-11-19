package com.jjcc.zsl.cloud.rabc.controller;

import com.jjcc.zsl.cloud.rabc.feign.SmsService;
import com.jjcc.zsl.cloud.rabc.service.dto.DemoDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.omg.CORBA.TIMEOUT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @className: SysDemoHystrixController.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-11-17 17:15
 */
@Log4j2
@RestController
@RequestMapping("/api/hystrix")
public class SysDemoHystrixController {

    private final SmsService smsService;

    public SysDemoHystrixController(SmsService smsService) {
        this.smsService = smsService;
    }

    /**
     * HystrixCommand注解：服务容错配置注解。注解配置方式大于配置文件方式
     *  fallbackMethod：指定 fallback 服务降级的处理方法，处理相应的异常。
     * @title getUser
     * @author Jjcc
     * @param userId 参数
     * @return org.springframework.http.ResponseEntity<java.lang.Object>
     * @createTime 2020/11/17 17:39
     */
    @GetMapping
    @HystrixCommand(fallbackMethod = "getUserFallback",
            commandProperties = {
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //统计窗口时间
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //启用熔断功能
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),  //20个请求触发熔断判断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //请求错误率超过60%触发熔断
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "300000"),//熔断后开始尝试恢复的时间
            })
    public ResponseEntity<Object> getUser(String userId) {
        // 抛出异常会执行指定的 fallback 方法
        int i = 1 / 0;
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    public ResponseEntity<Object> getUserFallback(String userId, Throwable throwable) {
        log.info("[getUserFallback][userId({}) exception({})]", userId, ExceptionUtils.getRootCauseMessage(throwable));
        return new ResponseEntity<>("请求接口失败", HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<Object> commonFallbackMethod() {
        return new ResponseEntity<>("请求接口失败", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("userName")
    @HystrixCommand(fallbackMethod = "getUserFallback", commandKey = "getUserName")
    public ResponseEntity<Object> getUserName(String userId) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        log.info("[getUserName][准备调用getUserName 方法]");
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setUsername("jjcc");
        demoDTO.setPassword("123");
        smsService.getDemo(demoDTO);
        return new ResponseEntity<>("!!!", HttpStatus.OK);
    }
}
