package com.tang.missu.core.configuration;

import com.tang.missu.core.interceptors.PermissionInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 将写好的interceptor类注册到spring中 */
@Component
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册interceptor
        registry.addInterceptor(new PermissionInterceptor());
    }
}
