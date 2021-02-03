package com.jjcc.zsl.cloud.config.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 自定义局部过滤器：路由后响应时执行 Post
 * @className: ResponseTimeGatewayFilterFactory.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2021-01-31 23:20
 */
@Component
@Order(1)
public class ResponseTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<ResponseTimeGatewayFilterFactory.Config> {

    public ResponseTimeGatewayFilterFactory() {
        super(ResponseTimeGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(ResponseTimeGatewayFilterFactory.Config config) {
        return ((exchange, chain) ->  {
            /* 此处编写的代码是pre过滤器执行的部分 */
            // 开始时间
            long startTime = System.currentTimeMillis();
            System.out.println("响应时间计算前过滤器");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                /* then方法中编写的代码是post过滤器执行的部分 */
                // 结束时间
                long endTIme = System.currentTimeMillis();
                System.out.println("响应时间计算后过滤器");
                System.out.println("响应时间：" + (endTIme - startTime));
            }));
        });
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }
}
