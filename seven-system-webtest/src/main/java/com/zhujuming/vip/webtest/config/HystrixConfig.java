package com.zhujuming.vip.webtest.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragonlai on 2019/3/26.
 */
@Configuration
public class HystrixConfig {
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet hys = new HystrixMetricsStreamServlet();
        ServletRegistrationBean source = new ServletRegistrationBean(hys);
        source.setLoadOnStartup(2);
        source.addUrlMappings("/hystrix.stream");
        source.setName("HystrixMetricsStreamServlet");
        return source;
    }
}
