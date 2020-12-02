package com.jjcc.zsl.cloud.rabc.serivce.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.jjcc.zsl.cloud.common.exception.BadRequestException;
import com.jjcc.zsl.cloud.rabc.serivce.UserService;
import org.springframework.stereotype.Service;

/**
 * @className: UserServiceImpl.java
 * @program: alibaba-cloud-suduty
 * @author: Jjcc
 * @create: 2020-11-26 16:52
 */
@Service
public class UserServiceImpl implements UserService {


    /**
     * sentinelResource注解：将此方法标注为sentinel的资源
     * @title getUserInfo
     * @author Jjcc
     * @return void
     * @createTime 2020/11/26 16:54
     */
    @Override
    @SentinelResource("userInfo")
    public void getUserInfo() {
    }
}
