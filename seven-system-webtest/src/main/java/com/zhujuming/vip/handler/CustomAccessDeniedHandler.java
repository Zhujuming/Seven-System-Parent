package com.zhujuming.vip.handler;

import com.zhujuming.vip.constants.ResponseCode;
import com.zhujuming.vip.utils.ResponseWriterUtil;
import com.zhujuming.vip.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zx
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        log.error("CustomAccessDeniedHandler ==============================>>>>>>  ", e);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.warn("User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
        }
        if (StringUtils.isBlank(response.getHeader("Access-token"))) {
            response.setHeader("Access-token", request.getHeader("Content-token"));
        }
        ResponseVO responseVO = new ResponseVO(ResponseCode.ACCESS_DENIED);
        ResponseWriterUtil.writer(response, responseVO);
    }
}
