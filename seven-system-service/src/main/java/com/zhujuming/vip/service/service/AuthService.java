package com.zhujuming.vip.service.service;


import com.zhujuming.vip.common.model.TUsersEntity;
import com.zhujuming.vip.common.vo.ResponseVO;

public interface AuthService {

    ResponseVO<TUsersEntity> getUserInfo(long userId);

    ResponseVO<TUsersEntity> getAllAuthorityByUserId(long userId);


}
