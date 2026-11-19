package com.tang.missu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
}
