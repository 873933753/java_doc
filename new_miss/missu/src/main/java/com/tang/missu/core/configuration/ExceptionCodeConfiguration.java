package com.tang.missu.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//将配置类和配置文件关联
@PropertySource(value = "classpath:config/exception-code.properties") // 将类和配置文件关联
@ConfigurationProperties("hanber") // 指定前缀，因为关联只能关联codes，需要指定前缀hanber.codes
@Component
public class ExceptionCodeConfiguration {
    private Map<Integer,String> codes = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }

    // 根据传入code返回message
    public String getMessage(int code){
        // map中code对应的message
        String message= codes.get(code);
        return message;
    }
}
