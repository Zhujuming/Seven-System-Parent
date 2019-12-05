package com.zhujuming.vip.service;


import com.zhujuming.vip.model.TUsersEntity;
import com.zhujuming.vip.vo.ResponseVO;

public interface AuthService {

    ResponseVO<TUsersEntity> getUserInfo(long userId);

    ResponseVO<TUsersEntity> getAllAuthorityByUserId(long userId);


}
