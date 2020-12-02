package com.jjcc.zsl.cloud.rabc.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义一个BlockException处理器；直接抛出异常。
 * @className: CustomBlockExceptionHandler.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-12-01 17:55
 */
@Component
public class CustomBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        throw e;
    }
}
