package com.zhujuming.vip.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 创建Token,jwt核心代码
 * @Author: v_jumingzhu
 * @CreateDate: 2019/9/24$ 17:14$
 * @Version: 1.0
 */
public class JwtTokenUtils {

    /**
     * token过期时间，单位：秒。这个值表示1小时
     */
    private static final long TOKEN_EXPIRED_TIME = 1 * 1 * 60 * 60;

    /**
     * jwt加密解密密钥
     */
    private static final String JWT_SECRET = "zhujuming";

    public static final String jwtId = "tokenId";


    /**
     * 生成JWT
     *
     * @param subject
     * @return
     */
    public static String createJWT(String subject, String roles) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(jwtId)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET).claim("roles", roles);
        if (TOKEN_EXPIRED_TIME > 0) {
            builder.setExpiration( new Date( nowMillis + TOKEN_EXPIRED_TIME));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public static Claims parseJWT(String jwtStr){
        return  Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

    /**
     * 创建JWT
     * @param claims
     * @param time
     * @return
     */
    public static String createJwtToken(Map<String, Object> claims, Long time) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());
        SecretKey secretKey = generalKey();
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(jwtId)               //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //jwt的签发时间
                .signWith(signatureAlgorithm, secretKey);//设置签名使用的签名算法和签名使用的秘钥
        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return secretKey;
    }

    /**
     * 验证jwt
     */
    public static Claims verifyJwt(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey secretKey = generalKey();
        Claims claims;
        try {
            claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(secretKey)//设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            claims = null;
        }//设置需要解析的jwt
        return claims;
    }

    /**
     * 据userId和password生成token
     * @param phone 电话号码
     * @param password 用户密码
     * @return
     */
    public static Map createClaims(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        return map;
    }
}
