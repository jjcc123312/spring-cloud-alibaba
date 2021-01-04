package com.jjcc.zsl.cloud.rabc.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jjcc.zsl.cloud.rabc.domain.OrderProperties;
import com.jjcc.zsl.cloud.rabc.feign.SmsService;
import com.jjcc.zsl.cloud.rabc.handler.SysUserControllerHandler;
import com.jjcc.zsl.cloud.rabc.serivce.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    private final UserService userService;

    public SysUserController(SmsService smsService,
                             OrderProperties orderProperties,
                             UserService userService) {
        this.smsService = smsService;
        this.orderProperties = orderProperties;
        this.userService = userService;
    }



    @GetMapping(value = "/pwd/reset")
    public ResponseEntity<Object> pwdreset(@RequestParam Integer userId) {
//        sysuserService.pwdreset(userId);
        String val = smsService.send("123", "更换密码");
        return new ResponseEntity<>(val, HttpStatus.OK);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<Object> test() {
        System.out.println("payTimeoutSeconds: " + payTimeoutSeconds);
        System.out.println("createFrequencySeconds: " + createFrequencySeconds);

        return new ResponseEntity<>("!!!!!!!!!!", HttpStatus.OK);
    }

    @GetMapping("chainFlowControl1")
    @SentinelResource(value = "chainFlowControl1Resource",
            blockHandlerClass = {SysUserControllerHandler.class}, blockHandler = "chainFlowControlBlockHandler")
    public ResponseEntity<Object> chainFlowControlTest1() {
        userService.getUserInfo();
        return new ResponseEntity<>("!!!!!!!!!!", HttpStatus.OK);
    }

    @GetMapping("chainFlowControl2")
    @SentinelResource(value =  "chainFlowControlTest2Resource",
            blockHandler = "chainFlowControlBlockHandler")
    public ResponseEntity<Object> chainFlowControlTest2() {
        userService.getUserInfo();
        return new ResponseEntity<>("??????????", HttpStatus.OK);
    }

    public ResponseEntity<Object> chainFlowControlBlockHandler(BlockException blockException) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("message", "系统服务繁忙！请稍后再试！");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("chainFlowControl3")
    public ResponseEntity<Object> chainFlowControlTest3() {
        userService.getUserInfo();
        return new ResponseEntity<>("123321", HttpStatus.OK);
    }
}
