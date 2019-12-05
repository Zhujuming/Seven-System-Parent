package com.zhujuming.vip.controller.auth;

import com.zhujuming.vip.consumer.auth.AuthConsumer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${common.cost.data.costService}")
    private String costService;
    @Value("${common.cost.data.userToggles}")
    private String userToggles;
    @Value("${expense.auth.gate.token}")
    private String gateToken;
    @Value("${expense.auth.gate.staffId}")
    private Long staffId;
    @Value("${costConstant.costV7ApiId}")
    private String costV7ApiId;
    @Value("${costConstant.costV7BatchId}")
    private String costV7BatchId;


}
