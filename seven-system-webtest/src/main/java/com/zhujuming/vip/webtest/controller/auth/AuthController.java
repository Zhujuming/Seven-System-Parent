package com.zhujuming.vip.webtest.controller.auth;


import com.zhujuming.vip.contract.consumer.auth.AuthConsumer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zx
 */
@RestController
@Slf4j
@Api(description = "获取token")
public class AuthController {

    @Resource
    private AuthConsumer authConsumer;


}
