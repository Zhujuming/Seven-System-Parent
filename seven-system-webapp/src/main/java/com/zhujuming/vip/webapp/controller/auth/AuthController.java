package com.zhujuming.vip.webapp.controller.auth;


import com.zhujuming.vip.common.constants.ResponseCode;
import com.zhujuming.vip.common.constants.TokenEnum;
import com.zhujuming.vip.common.exception.TokenException;
import com.zhujuming.vip.common.model.TUsersEntity;
import com.zhujuming.vip.common.utils.SHA256Util;
import com.zhujuming.vip.common.utils.ValidationUtil;
import com.zhujuming.vip.common.vo.MGateToken;
import com.zhujuming.vip.common.vo.ResponseVO;
import com.zhujuming.vip.webapp.controller.TokenBuilder;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Slf4j
@RestController
@Api(description = "获取token")
public class AuthController {

//    @Autowired
//    private AuthConsumer authConsumer;

    @Value("${config.get.tokenKey}")
    private String tokenKey;

    private Long userId = 13902434787l;

    @PostMapping("getToken")
    public ResponseVO token(HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(response.getHeader("Access-token"))) {
            MGateToken mGateToken;
            if (userId != null) {
                log.info("\n>>>>>> validate token. set default staffId : " + userId);
                mGateToken = new MGateToken(0, "", userId, "", "");
            } else {
                mGateToken = validateToken(request);
            }
            TUsersEntity userInfo = getUserInfo(mGateToken.getUserId());
            response.setHeader("Access-token", TokenBuilder.builderToken(userInfo));

            String userInfoStr = Jwts.parser()
                    .setSigningKey(TokenEnum.SIGNING_KEY.getKey())
                    .parseClaimsJws(TokenBuilder.builderToken(userInfo))
                    .getBody()
                    .getSubject();

            return new ResponseVO<>(ResponseCode.OK, userInfo);
        }
        return new ResponseVO<>(ResponseCode.OK);
    }

    @GetMapping("/userInfo/{userId}")
    public ResponseVO userInfo(@PathVariable(value = "userId") long userId) {
//        return authConsumer.getUserInfo(userId);
        return null;
    }

    private MGateToken validateToken(HttpServletRequest request) {
        log.info(">>>>>>  validate token. start validate.");
        MGateToken mGateToken = validateRequest(request);
        if (validate(mGateToken, tokenKey)) {
            log.info(">>>>>>  validate token. validate success.");
            return mGateToken;
        }
        throw new TokenException(ResponseCode.AUTH_INVALID.value(), "移动网关认证异常");
    }

    private MGateToken validateRequest(HttpServletRequest request) {
        String timestamp = request.getHeader("Timestamp");
        String signature = request.getHeader("Signature");
        String userId = request.getHeader("userId");
        String userName = request.getHeader("userName");
        String password = request.getHeader("password");
        log.info(String.format(">>>>>>  validateRequest - " +
                "timestamp : %s, signature : %s, userId : %s, userName : %s, openId : %s, seq : %s", timestamp, signature, userId, userName));

        ValidationUtil.assertStringSize(timestamp, 0, ~(-1L << 63), "移动网关认证异常");
        ValidationUtil.assertBlank(signature, "移动网关认证异常");
        ValidationUtil.assertStringSize(userId, 0, ~(-1L << 63), "移动网关认证异常");
        ValidationUtil.assertBlank(userName, "移动网关认证异常");
        log.info("==============================>>>>>>  MGate token. request params validate success.");
        return new MGateToken(Long.valueOf(timestamp), signature, Long.valueOf(userId), userName,password);
    }

    private boolean validate(MGateToken mGateToken, String gateToken) {
        return Math.abs(mGateToken.getTimestamp() - Calendar.getInstance().getTimeInMillis() / 1000) <= 180
                && mGateToken.getSignature().equalsIgnoreCase(SHA256Util.getSHA256StrJava(
                String.format("%s%s%s,%s,%s,%s%s",
                        mGateToken.getTimestamp(),
                        gateToken,
                        mGateToken.getUserId(),
                        mGateToken.getUserName(),
                        mGateToken.getPassword(),
                        mGateToken.getTimestamp())));
    }

    private TUsersEntity getUserInfo(long userId) {
        log.info("\n>>>>>>  MGate token.  >>>  builder business token.  >>>  get staff info by staffId : " + userId);
//        ResponseVO<TUsersEntity> userInfo = authConsumer.getUserInfo(userId);
//        if (null == userInfo) {
//            throw new TokenException(ResponseCode.AUTH_INVALID.value(), "user info error");
//        }
//        return userInfo.getData();
        return null;
    }
}
