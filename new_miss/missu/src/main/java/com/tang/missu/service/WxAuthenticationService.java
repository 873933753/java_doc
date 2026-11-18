package com.tang.missu.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;



/* 微信登录验证 */
@Service
public class WxAuthenticationService {
    @Autowired
    private ObjectMapper mapper;


    /* 注入配置文件的参数 */
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

        return null;
    }
}
