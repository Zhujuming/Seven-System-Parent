package com.zhujuming.vip.contract.hystrix.auth;

import com.zhujuming.vip.common.constants.ResponseCode;
import com.zhujuming.vip.common.model.TUsersEntity;
import com.zhujuming.vip.common.vo.ResponseVO;
import com.zhujuming.vip.contract.consumer.auth.AuthConsumer;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 熔断器类
 */
@Slf4j
@Component
public class AuthHystrix implements FallbackFactory<AuthConsumer> {

    @Override
    public AuthConsumer create(Throwable throwable) {
        String msg = throwable == null ? "" : throwable.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            log.error(msg);
        }
        ResponseCode rc = ResponseCode.REMOTE_CALL_FAIL;
        ResponseVO rtn = new ResponseVO<>(rc);
        return new AuthConsumer() {

            @Override
            public ResponseVO<TUsersEntity> getUserInfo(long userId) {
                return rtn;
            }

            @Override
            public ResponseVO<TUsersEntity> getAllAuthorityByUserId(Long userId) {
                return rtn;
            }
        };
    }
}
