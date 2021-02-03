package com.jjcc.zsl.cloud.config.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义局部过滤器；路由之前执行 prefix
 * @className: AuthGatewayFilterFactory.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2021-01-27 20:27
 */
@Component
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config> {

    public AuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // <1> token 和 userId 的映射
        Map<String, Integer> tokenMap = new HashMap<>(4);
        tokenMap.put("jjcc", 13);
        return (exchange, chain) -> {
            /* 此处编写的代码是pre过滤器执行的部分 */
            System.out.println("token校验前过滤器");
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            // <2> 获得 token
            String token = headers.getFirst(config.getTokenHeaderName());

            // <3> 如果没有 token，则不进行认证。因为可能是无需认证的 API 接口
            if (!StringUtils.hasText(token)) {
                return chain.filter(exchange);
            }

            // <4> 进行认证
            ServerHttpResponse response = exchange.getResponse();
            Integer userId = tokenMap.get(token);
            if (Objects.isNull(userId)) {
                // <5> 认证失败
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap("认证失败！".getBytes());
                return response.writeWith(Flux.just(buffer));
            }

            // <6> 认证通过，将 userId 添加到 Header 中
            request = request.mutate().header(config.getUserIdHeaderName(), String.valueOf(userId)).build();
            return chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable(() -> {
                /* then方法中编写的代码是post过滤器执行的部分 */
                System.out.println("token校验后过滤器");
            }));
        };
    }

    public static class Config {

        private static final String DEFAULT_TOKEN_HEADER_NAME = "token";
        private static final String DEFAULT_HEADER_NAME = "user-id";

        private String tokenHeaderName = DEFAULT_TOKEN_HEADER_NAME;
        private String userIdHeaderName = DEFAULT_HEADER_NAME;

        public String getTokenHeaderName() {
            return tokenHeaderName;
        }

        public String getUserIdHeaderName() {
            return userIdHeaderName;
        }

        public Config setTokenHeaderName(String tokenHeaderName) {
            this.tokenHeaderName = tokenHeaderName;
            return this;
        }

        public Config setUserIdHeaderName(String userIdHeaderName) {
            this.userIdHeaderName = userIdHeaderName;
            return this;
        }

    }
}
