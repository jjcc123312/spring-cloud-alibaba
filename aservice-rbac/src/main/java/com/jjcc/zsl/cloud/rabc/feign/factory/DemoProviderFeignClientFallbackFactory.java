package com.jjcc.zsl.cloud.rabc.feign.factory;

import com.jjcc.zsl.cloud.rabc.feign.SmsServiceFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @className: DemoProviderFeignClientFallbackFactory.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2021-01-04 14:28
 */
@Component
public class DemoProviderFeignClientFallbackFactory implements FallbackFactory<SmsServiceFallback> {

    @Override
    public SmsServiceFallback create(Throwable throwable) {
        return new SmsServiceFallback(throwable);
    }
}
