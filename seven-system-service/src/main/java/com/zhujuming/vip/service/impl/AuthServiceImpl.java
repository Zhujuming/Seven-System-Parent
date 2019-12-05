package com.zhujuming.vip.service.impl;


import com.zhujuming.vip.model.TUsersEntity;
import com.zhujuming.vip.vo.ResponseVO;
import com.zhujuming.vip.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public ResponseVO<TUsersEntity> getUserInfo(long userId) {

        return null;
    }

    @Override
    public ResponseVO<TUsersEntity> getAllAuthorityByUserId(long userId) {

        return null;
    }
}
