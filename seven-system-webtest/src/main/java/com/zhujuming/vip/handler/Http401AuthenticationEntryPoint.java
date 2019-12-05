package com.zhujuming.vip.handler;

import com.zhujuming.vip.constants.ResponseCode;
import com.zhujuming.vip.utils.ResponseWriterUtil;
import com.zhujuming.vip.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zx
 */
@Slf4j
public class Http401AuthenticationEntryPoint implements AuthenticationEntryPoint {

    private String headerValue;

    public Http401AuthenticationEntryPoint() {
    }

    public Http401AuthenticationEntryPoint(String headerValue) {
        this.headerValue = headerValue;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        log.error("Http401AuthenticationEntryPoint ==============================>>>>>>  {}" + authException);
        if (StringUtils.isBlank(response.getHeader("Access-token"))) {
            response.setHeader("Access-token", StringUtils.isNotBlank(headerValue) ? headerValue : request.getHeader("Content-token"));
        }
        ResponseVO responseVO = new ResponseVO(ResponseCode.AUTH_INVALID);
        ResponseWriterUtil.writer(response, responseVO);
    }

}
