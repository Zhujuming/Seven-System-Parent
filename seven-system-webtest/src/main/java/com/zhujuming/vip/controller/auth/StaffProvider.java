package com.zhujuming.vip.controller.auth;

import com.zhujuming.vip.exception.TokenException;
import com.zhujuming.vip.model.StaffInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by zx
 */
@Slf4j
public class StaffProvider {

    public static StaffInfo staffInfo() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (StaffInfo)authentication.getPrincipal();
        } catch (Exception e) {
            log.warn("Authentication get staff info error.", e);
            throw new TokenException("人员信息错误");
        }
    }

}
