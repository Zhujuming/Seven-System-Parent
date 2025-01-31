package com.zhujuming.vip.controller.auth;

import com.zhujuming.vip.constants.TokenEnum;
import com.zhujuming.vip.model.TAllStaffEntity;
import com.zhujuming.vip.utils.Serializer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zx
 */
public class TokenBuilder {

    public static String builder(TAllStaffEntity staffInfo) {
        // builder the token
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 30);
        Date time = calendar.getTime();
        return Jwts.builder()
                .setSubject(Serializer.serialize(staffInfo))
                .setIssuedAt(now)
                .setExpiration(time)
                .signWith(SignatureAlgorithm.HS512, TokenEnum.SIGNING_KEY.getKey()) //不一定非要采用HS512
                .compact();
    }
}
