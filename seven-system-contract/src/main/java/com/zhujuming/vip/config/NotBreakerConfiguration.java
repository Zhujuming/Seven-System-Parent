package com.zhujuming.vip.config;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.zhujuming.vip.exception.InternalApiException;
import com.zhujuming.vip.utils.ExceptionUtil;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.io.IOException;


/**
 * @Description: feign 服务异常不进入熔断
 * @Date: Created in 1:29 2018/6/2
 * 不进入熔断，直接抛出异常
 * 有时我们并不希望方法进入熔断逻辑，只是把异常原样往外抛。
 * 这种情况我们只需要捉住两个点：不进入熔断、原样。
 * 原样就是获取原始的异常,而不进入熔断,需要把异常封装成HystrixBadRequestException，
 * 对于HystrixBadRequestException，Feign会直接抛出，不进入熔断方法。
 */
@Slf4j
public class NotBreakerConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }

    /**
     * 自定义错误解码器
     */
    public class UserErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = null;
            if (response.status() != HttpStatus.OK.value()) {
                try {
                    //获取原始的返回内容(来自GlobalExceptionHandler类中的返回信息)
                    String json = Util.toString(response.body().asReader());
                    InternalApiException internalApiException = JSON.parseObject(json, InternalApiException.class);
                    log.info(" 【===Server Exception===】,{}", ExceptionUtil.getErrorMessage(internalApiException));
                    exception = new HystrixBadRequestException(internalApiException.getMessage());
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                    exception = new HystrixBadRequestException(e.getMessage());
                }
            }
            return exception;
        }
    }
}

