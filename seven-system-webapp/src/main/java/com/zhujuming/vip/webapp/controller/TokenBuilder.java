package com.zhujuming.vip.webapp.controller;

import com.zhujuming.vip.common.constants.TokenEnum;
import com.zhujuming.vip.common.model.TUsersEntity;
import com.zhujuming.vip.common.utils.Serializer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

public class TokenBuilder {

    //builder the token
    public static String builderToken(TUsersEntity usersInfo) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 30);
        Date time = calendar.getTime();
        return Jwts.builder()
                .setSubject(Serializer.serialize(usersInfo))
                .setIssuedAt(now)
                .setExpiration(time)
                .signWith(SignatureAlgorithm.HS512, TokenEnum.SIGNING_KEY.getKey()) //不一定非要采用HS512
                .compact();
    }
}
