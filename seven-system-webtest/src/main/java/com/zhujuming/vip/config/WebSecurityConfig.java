package com.zhujuming.vip.config;

import com.zhujuming.vip.handler.Http401AuthenticationEntryPoint;
import com.zhujuming.vip.filter.AuthenticationFilter;
import com.zhujuming.vip.handler.CustomAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

/**
 * Created by zx
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 需要放行的URL
     */
    private static final String[] AUTH_WHITELIST = {
            // -- register url
//            "/**",
            "/token",
//            "/finance-apply/api/token",
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()  // 所有请求需要身份认证
                .and().exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint())
                .and().addFilter(new AuthenticationFilter(authenticationManager())).exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);// 自定义访问失败处理器
    }

}
