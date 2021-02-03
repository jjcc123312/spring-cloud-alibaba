package com.jjcc.zsl.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

/**
 * 代码方式配置gateway route
 * @className: RouteLocatorConfig.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2021-01-25 15:50
 */
@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p ->
                        // 谓词
                        p.path("/fluent/customer/**")
                                // 过滤器
                                .filters(f -> f.stripPrefix(2))
                                .uri("https://wwww.baidu.com")
                                .id("testConfig")
                                .order(0)
                ).build();
    }
}
















