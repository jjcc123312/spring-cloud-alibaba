package com.jjcc.zsl.cloud.rabc.config.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * nacos配置管理自动刷新监听
 * @className: DemoEnvironmentChangeEvent.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-11-08 20:48
 */
@Component
@Log4j2
public class DemoEnvironmentChangeEvent implements ApplicationListener<EnvironmentChangeEvent> {



    private final ConfigurableEnvironment environment;

    public DemoEnvironmentChangeEvent(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        for (String key : event.getKeys()) {
            log.info("[onApplicationEvent][key({}) 最新 value 为 {}]", key, environment.getProperty(key));
        }

    }
}
