package com.zhujuming.vip.webapp.config;

import com.zhujuming.vip.webapp.filter.AuthenticationFilter;
import com.zhujuming.vip.webapp.handler.CustomAccessDeniedHandler;
import com.zhujuming.vip.webapp.handler.Http401AuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 需要放行的URL
     */
    private static final String[] AUTH_WHITELIST = {
            "/token",
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

    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;
}
