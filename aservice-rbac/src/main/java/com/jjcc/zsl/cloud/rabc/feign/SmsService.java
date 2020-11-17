package com.jjcc.zsl.cloud.rabc.feign;

import com.jjcc.zsl.cloud.rabc.config.feignconfig.DemoProviderFeignClientConfiguration;
import com.jjcc.zsl.cloud.rabc.service.dto.DemoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign远程调用类
 * configuration: 设置当前feignClient的配置类
 * @className: SmsService.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-09-25 17:47
 */
@FeignClient(value = "aservice-sms", path = "/api/sms", configuration = DemoProviderFeignClientConfiguration.class)
public interface SmsService {

    @PostMapping(value = "/sms/send")
    String send(@RequestParam("phoneNo") String phoneNo,
                                @RequestParam("content") String content);

    @GetMapping(value = "get_demo")
    DemoDTO getDemo(@SpringQueryMap DemoDTO demoDTO);

    @PostMapping(value = "get_demo_querMapp")
    DemoDTO getDemoQuerMapp(@SpringQueryMap DemoDTO demoDTO);

    @PostMapping("/post_demo") // POST 方式
    DemoDTO postDemo(@RequestBody DemoDTO demoDTO);
}
