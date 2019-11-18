package com.zhujuming.vip.webtest.filter;

import com.alibaba.fastjson.JSON;
import com.zhujuming.vip.common.constants.TokenEnum;
import com.zhujuming.vip.webtest.aspect.StaffInfo;
import com.zhujuming.vip.webtest.controller.auth.TokenBuilder;
import com.zhujuming.vip.webtest.handler.GrantedAuthorityImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zx
 */
@Slf4j
public class AuthenticationFilter extends BasicAuthenticationFilter {

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        setHeader(response);
        String content_token = request.getHeader("Content-token");
        if (StringUtils.isNotBlank(content_token)) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(request, response));
        }
        chain.doFilter(request, response);
        String access_token = response.getHeader("Access-token");
        log.warn(" request content_token {},\n response Access-token {}", content_token, access_token);
    }

    private void setHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", CorsConfiguration.ALL);
        response.setHeader("Access-Control-Allow-Methods", CorsConfiguration.ALL);
        response.setHeader("Access-Control-Allow-Headers", CorsConfiguration.ALL);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Access-Control-Expose-Headers", "Access-token");
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Content-token");
        log.info("getAuthentication Content-token : {} ", token);
        String userInfo;
        try {
            long start = System.currentTimeMillis();
            val body = Jwts.parser()
                    .setSigningKey(TokenEnum.SIGNING_KEY.getKey())
                    .parseClaimsJws(token)
                    .getBody();
            userInfo = body.getSubject();
            long end = System.currentTimeMillis();
            log.info("==============================>>>>>>  getAuthentication. 执行时间: " + (end - start) + " 毫秒" + " >>>  parse the token success. user info : " + userInfo);
            if (StringUtils.isNotBlank(userInfo)) {
                StaffInfo staffInfo = JSON.parseObject(userInfo, StaffInfo.class);
                List<GrantedAuthorityImpl> authorities = new ArrayList<>();
                long expiration = body.getExpiration().getTime();
                long now = Calendar.getInstance().getTime().getTime();
                log.info("==============================>>>>>>  getAuthentication.  >>>  expiration : {} - now : {} - difference : {}.", expiration, now, expiration - now);
                if ((expiration - now) < 10 * 60 * 1000) {
                    token = TokenBuilder.builder(staffInfo);
                }


                response.setHeader("Access-token", token);
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(staffInfo, null, authorities);
                log.info("==============================>>>>>>  getAuthentication.  >>>  parse the token and set UsernamePasswordAuthenticationToken success.");
                return upat;
            }
        } catch (ExpiredJwtException e) {
            log.warn("==============================>>>>>> getAuthentication Token已过期", e);
        } catch (UnsupportedJwtException e) {
            log.warn("==============================>>>>>> getAuthentication Token格式错误", e);
        } catch (MalformedJwtException e) {
            log.warn("==============================>>>>>> getAuthentication Token没有被正确构造", e);
        } catch (SignatureException e) {
            log.warn("==============================>>>>>> getAuthentication 签名失败", e);
        } catch (IllegalArgumentException e) {
            log.warn("==============================>>>>>> getAuthentication 非法参数异常", e);
        } catch (Exception e) {
            log.error("==============================>>>>>> getAuthentication Exception", e);
        }
        return null;
    }

}
