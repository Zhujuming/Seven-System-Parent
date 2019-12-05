package com.zhujuming.vip.consumer.auth;


import com.zhujuming.vip.model.TUsersEntity;
import com.zhujuming.vip.vo.ResponseVO;
import com.zhujuming.vip.config.NotBreakerConfiguration;
import com.zhujuming.vip.hystrix.auth.AuthHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "seven-system-service",fallbackFactory = AuthHystrix.class,configuration = {NotBreakerConfiguration.class})
//public interface AuthConsumer {
//
//    @PostMapping("/getUserInfo")
//    ResponseVO<TUsersEntity> getUserInfo(@RequestBody long userId);
//
//    @PostMapping("/getToken")
//    ResponseVO<TUsersEntity> getAllAuthorityByUserId(@RequestBody Long userId);
@FeignClient(value = "seven-system-service", fallbackFactory = AuthHystrix.class,configuration = {NotBreakerConfiguration.class})
public interface AuthConsumer {

    @PostMapping("/getUserInfo")
    ResponseVO<TUsersEntity> getUserInfo(@RequestBody long userId);

    @PostMapping("/getToken")
    ResponseVO<TUsersEntity> getAllAuthorityByUserId(@RequestBody Long userId);

}
