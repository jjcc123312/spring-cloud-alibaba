package com.jjcc.zsl.cloud.rabc.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: SysUserControllerHandler.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-12-01 17:38
 */
public class SysUserControllerHandler {

    public static ResponseEntity<Object> chainFlowControlBlockHandler(BlockException blockException) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("message", "系统服务繁忙！请稍后再试！");
        return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ResponseEntity<Object> errorHandler(int a, BlockException blockException) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("message", "blockHandler:系统服务繁忙！请稍后再试！");
        return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    }
}
