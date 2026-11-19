package com.tang.missu.core.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.tang.missu.exception.http.ForbiddenException;
import com.tang.missu.exception.http.UnAuthenticatedException;
import com.tang.missu.utils.JwtToken;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/* 请求拦截器 */
// 需要继承或实现HandlerInterceptor方法
public class PermissionInterceptor implements HandlerInterceptor {

    /* 三个方法的执行顺序
    * pre
    *   request  = 当前请求，读参数、头、session、URI
        response = 当前响应，写状态码、响应体、重定向
        handler  = 即将执行的处理器，通常是 HandlerMethod，代表某个 Controller 方法
    *  */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 获取api的scopeLevel */
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        // 如果scopeLevel不存在-即为公开接口
        if (!scopeLevel.isPresent()){
            return true;// 公开接口，不做拦截返回true
        }

        /* 获取前端传的token - 非公开接口 */
        String bearerToken = request.getHeader("Authorization");
        // token判断
        if (StringUtils.isEmpty(bearerToken)){
            throw new UnAuthenticatedException(10004);
        }
        // 判断是否以Bearer开头
        if (!bearerToken.startsWith("Bearer")){
            throw new UnAuthenticatedException(10004);
        }
//        String token = bearerToken.split(" ")[1]; // 截取出token
        // 数组越界的问题 - 先需要判断数组长度
        String tokens[] = bearerToken.split(" ");
        if(!(tokens.length == 2)){
            throw new UnAuthenticatedException(10004);
        }
        String token = tokens[1];


        /* 解析token */
        // 校验令牌 - 非空校验通过
        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);
        // 判空 - 校验不通过
        Map<String,Claim> map = optionalMap.orElseThrow(() -> new UnAuthenticatedException(10004));

        /* 判断是否有权限 */
        boolean valid = this.hasPermission(scopeLevel.get(),map);
        return valid;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /* 自定义方法获取注解ScopeLevel中的值 */
    private Optional<ScopeLevel> getScopeLevel(Object handler){
        // 先判断是否是HandlerMethod类型
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 可以读取 Controller 方法上的注解
            ScopeLevel scopeLevel = handlerMethod.getMethodAnnotation(ScopeLevel.class);
            if (scopeLevel == null){ // 要尽早找到空值
                return Optional.empty();
            }
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }

    /* 自定义方法判断scope和level的值- 是否有权限 */
    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map) {
        Integer level = scopeLevel.value();
        Integer scope = map.get("scope").asInt();
        if (level > scope) {
            throw new ForbiddenException(10005);
        }
        return true;
    }
}
