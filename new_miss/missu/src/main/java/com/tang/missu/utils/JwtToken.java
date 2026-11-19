package com.tang.missu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/* Jwt令牌生成，验证 */
@Component // 不加@Component，启动不会扫@Value
public class JwtToken {

    /* 注入secret配置参数 */
    private static String jwtKey;

    @Value("${missu.security.jwt-key}")
    public void setJwtKey(String jwtKey){
        JwtToken.jwtKey = jwtKey;
    }

    /* 注入过期时间 */
    private static Integer expiredTimeIn;

    @Value("${missu.security.token-expired-in}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtToken.expiredTimeIn = expiredTimeIn;
    }

    // 设置一个默认等级
    private static Integer defaultScope = 8;



    /* ---生成JWT令牌--- */
    // 接收两个参数，一个uid，一个scope（用户等级 scopeLevel）
    public static String makeToken(Long uid){
        // 调用生成逻辑
        return JwtToken.getToken(uid,JwtToken.defaultScope);
    }

    /* 生成令牌的逻辑。 */
    public static String getToken(Long uid, Integer scope){
        // 生成令牌的两个库：jjwt，auth0(推荐) - jwt.io

        // 选择加密算法，secret随机字符串写在配置文件中
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        // 计算过期时间
        Map<String,Date> map = JwtToken.calculateExpiredIssues();
        // 生成令牌
        String jwt = JWT.create()
                .withClaim("uid",uid)
                .withClaim("scope",scope)
                .withExpiresAt(map.get("expiredTime")) // 在哪个时间点过期
                .withIssuedAt(map.get("now")) // 令牌签发时间 now
                .sign(algorithm);
        return  jwt;
    }

    /* 自定义方法计算令牌过期时间和签发时间（签发时间可以不写） */
    private static Map<String, Date> calculateExpiredIssues() {
        Map<String, Date> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND, JwtToken.expiredTimeIn);
        map.put("now", now);
        map.put("expiredTime", calendar.getTime());
        return map;
    }

    /*---校验令牌---*/
    // 获取token并解析令牌，令牌校验未
    public static Optional<Map<String, Claim>> getClaims(String token){
        // 解析令牌
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        // 读取token
        DecodedJWT decodedJWT;
        // 异常处理 - verify可能是校验失败异常
        try {
            decodedJWT = jwtVerifier.verify(token);
        }catch (JWTVerificationException e) {
            return Optional.empty(); // Optional 返回空值
        }
        return Optional.of(decodedJWT.getClaims()); // Optional 返回非空值
    }


    /* 验证token是否有效 */
    public static Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }
}
