//package com.zhujuming.vip.master.service;
//
//import com.zhujuming.vip.common.constants.TokenEnum;
//import com.zhujuming.vip.common.model.TUsersEntity;
//import com.zhujuming.vip.common.utils.Serializer;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//
//
///**
// * @Description: token 下发
// * @Author: v_jumingzhu
// * @CreateDate: 2019/9/24$ 16:23$
// * @UpdateDate: 2019/9/24$ 16:23$
// * @UpdateRemark: 修改内容
// * @Version: 1.0
// */
//public class TokenService {
//
//    public String getToken(TUsersEntity users){
//        Date startTime = new Date();
//        //设置有效时间为一小时
//        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
//        Date endTime = new Date(currentTime);
//        String token = "";
//
//        Jwts.builder()
//                .setSubject(Serializer.serialize(staffInfo))
//                .setIssuedAt(now)
//                .setExpiration(time)
//                .signWith(SignatureAlgorithm.HS512, TokenEnum.SIGNING_KEY.getKey())
//                .compact();
//
//
////        token = JWT.create().withAudience(users.getUserId()).withIssuedAt(startTime).withExpiresAt(endTime)
////                .sign(Algorithm.HMAC256(users.getPassword()));
//        return token;
//    }
//}
