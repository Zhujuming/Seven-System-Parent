package com.zhujuming.vip.service.controller.auth;


import com.zhujuming.vip.common.constants.ResponseCode;
import com.zhujuming.vip.common.model.TUsersEntity;
import com.zhujuming.vip.common.vo.ResponseVO;
import com.zhujuming.vip.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/getStaffInfo")
    public ResponseVO getUserInfo(@RequestBody long userId) {
        if (userId == 0) {
            return new ResponseVO<>(ResponseCode.PARAM_INVALID);
        }
        return authService.getUserInfo(userId);
    }

    @PostMapping("/getToken")
    public ResponseVO getAllAuthorityByUserId(@RequestBody long userId){
        if (userId == 0) {
            return new ResponseVO<>(ResponseCode.PARAM_INVALID);
        }
        ResponseVO<TUsersEntity> allAuthorityByUserId = authService.getAllAuthorityByUserId(userId);
        return allAuthorityByUserId;
    }


}
