package com.zhujuming.vip.webapp.config;

import feign.Feign;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
@Slf4j
public class FeignOkHttpConfig {

    @Resource
    public OkHttp3LoggingInterceptor okHttp3LoggingInterceptor;

    /**
     * 日志级别
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * OkHttp 是一个高效的 HTTP 客户端，具有非常多的优势：
     *
     * 能够高效的执行 http，数据加载速度更快，更省流量
     * 支持 GZIP 压缩，提升速度，节省流量
     * 缓存响应数据，避免了重复的网络请求
     * 使用简单，支持同步阻塞调用和带回调的异步调用
     * @return
     */
    @Bean
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)  //设置读取超时时间
                .connectTimeout(60, TimeUnit.SECONDS) //设置连接超时时间
                .writeTimeout(120, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool())
                //.addInterceptor();  //添加请求拦截器
                .addNetworkInterceptor(okHttp3LoggingInterceptor)
                .build();
    }
}
