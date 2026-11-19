package com.tang.missu.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.missu.exception.http.ParameterException;
import com.tang.missu.model.User;
import com.tang.missu.respoitory.UserRepository;
import com.tang.missu.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/* 微信登录验证 */
@Service
public class WxAuthenticationService {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;


    /* 注入配置文件的微信链接参数 */
    @Value("${wx.code2Session}")
    private String code2SessionUrl;
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;

    // code 换 token，code 码无法伪造需要从小程序获取
    public String code2Session(String code){
        // 1. 调用微信服务器接口验证code码 - 参数在配置文件中
        // 拼接出完整调用微信服务器的url
        String url = MessageFormat.format(this.code2SessionUrl,this.appId,this.appSecret,code);
        // 后端发送请求
        RestTemplate rest = new RestTemplate();
        // 标明返回类型,返回的是字符串--- 返回{
        //  "session_key" : "xxx",
        //  "openid" : "xxx"
        //}
        String sessionText = rest.getForObject(url,String.class);

        Map<String, Object> session = new HashMap<>();
        try {
            // 反序列化：JSON 字符串 -> 对象
            session = mapper.readValue(sessionText, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return this.registerUser(session);
    }

    /* 获取返回的openid
    * 1）如果数据库有该openid的用户，根据该用户的UID生成token
    * 2）否则注册改用户 -> 写入用户，并生成UID生成Token
    *  */
    public String registerUser(Map<String, Object> session){
        // 获取用户的openId
        String openid = (String) session.get("openid"); //get 方法声明的返回类型是 Object，而 Java 不会自动把 Object 窄化成 String，需要显式强制类型转换。
        // 如果微信接口未返回openid
        if (openid == null){
            throw new ParameterException(20004);
        }
        // 查用户
        Optional<User> userOptional = this.userRepository.findUserByOpenid(openid);

        // 1）如果openid存在 - 返回JWT令牌
        if (userOptional.isPresent()){
            // "用户存在"
            return "用户存在：" + JwtToken.makeToken(userOptional.get().getId());
        }

        // 2）如果openid不存在 - 传入openid写入用户（目前只写入openid）
        User user = User.builder()
                .openid(openid)
                .build();
        userRepository.save(user);
        Long uid = user.getId();
        // 返回JWT令牌 - 获取jwt令牌
        String jwt = JwtToken.makeToken(uid);
        // "用户不存在"
        return "用户不存在：" + jwt;
    }




}
