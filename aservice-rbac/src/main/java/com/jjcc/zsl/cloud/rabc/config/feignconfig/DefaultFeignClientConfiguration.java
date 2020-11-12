package com.jjcc.zsl.cloud.rabc.config.feignconfig;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * java config方式配置feignClient
 * 全局方式配置 feignClient
 * @className: DefaultFeignClientConfiguration.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-11-11 16:36
 */
public class DefaultFeignClientConfiguration {

    @Bean
    public Logger.Level defaultFeignClientLoggerLevel() {
        // 设置feign打印日志级别
        return Logger.Level.BASIC;
    }

}
