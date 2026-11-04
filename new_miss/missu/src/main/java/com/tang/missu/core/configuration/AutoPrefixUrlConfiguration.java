package com.tang.missu.core.configuration;

import com.tang.missu.core.hack.AutoPrefixUrlMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/* 根据目录结构生成api前缀- 配置类 */
@Component
public class AutoPrefixUrlConfiguration implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//        return WebMvcRegistrations.super.getRequestMappingHandlerMapping();
          return new AutoPrefixUrlMapping();
    }
}
