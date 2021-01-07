package com.jjcc.zsl.cloud.rabc.feign;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @className: SmsServiceFallback.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2021-01-04 14:34
 */
@Log4j2
public class SmsServiceFallback implements SmsService{

    private final Throwable throwable;

    public SmsServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String send(String phoneNo, String content) {
        return "fallback:" + throwable.getClass().getSimpleName();
    }

    @Override
    public ResponseEntity<String> echo() {
        log.error(throwable);
        return new ResponseEntity<>("系统繁忙请稍后再试！", HttpStatus.NOT_ACCEPTABLE);
    }
}
