package com.jjcc.zsl.cloud.rabc.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义了 RequestOriginParser 接口，从请求中解析到调用来源，例如说使用 IP、请求头 user、请求头 appName。
 * @className: CustomRequestOriginParser.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-12-17 20:17
 */
@Component
public class CustomRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        // <X> 从 Header 中，获得请求来源
        String origin = request.getHeader("s-user");
        if (StringUtils.isBlank(origin)) {
            // <Y> 如果为空，给一个默认的
            origin = "default";
        }
        return origin;
    }
}
