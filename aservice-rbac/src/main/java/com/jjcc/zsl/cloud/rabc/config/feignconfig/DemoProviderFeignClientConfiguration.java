package com.jjcc.zsl.cloud.rabc.config.feignconfig;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 客户端级别配置 feignClient
 * @className: DemoProviderFeignClientConfiguration.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-11-11 16:56
 */
public class DemoProviderFeignClientConfiguration {


    @Bean
    @Primary // 主要的Bean
    public Logger.Level feignClientLoggerLevel() {
        return Logger.Level.FULL;
    }
}
