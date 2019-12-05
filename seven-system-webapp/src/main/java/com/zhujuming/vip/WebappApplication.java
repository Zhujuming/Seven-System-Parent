package com.zhujuming.vip;

import com.sun.jersey.spi.container.WebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

/**
 * @EnableAsync :启用异步任务
 * @EnableScheduling :开启定时任务
 * @ServletComponentScan ：使用@WebServlet、@WebFilter、@WebListener标记的 Servlet、Filter、Listener 就可以自动注册到Servlet容器中
 * @EnableFeignClients ：扫描和注册feign客户端bean定义
 * @EnableEurekaClient ：服务发现客户端
 * @EnableHystrixDashboard ：开启服务熔断
 * @EnableCircuitBreaker ：开启熔断超时机制，默认熔断机制是1S
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
@ServletComponentScan
@EnableFeignClients
@EnableEurekaClient
@EnableHystrixDashboard
@EnableCircuitBreaker
public class WebappApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        return factory.createMultipartConfig();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class);
    }
}

