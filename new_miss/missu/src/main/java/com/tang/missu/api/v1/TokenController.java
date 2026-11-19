package com.tang.missu.api.v1;


import com.tang.missu.dto.TokenDTO;
import com.tang.missu.dto.TokenGetDTO;
import com.tang.missu.exception.http.NotFoundException;
import com.tang.missu.service.WxAuthenticationService;
import com.tang.missu.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private WxAuthenticationService wxAuthenticationService;


    /* 获取token */
    @PostMapping("")
    public Map<String, String> getToken(@RequestBody @Validated TokenGetDTO userData) {
        Map<String, String> map = new HashMap<>();

        // 分发的token
        String token = null;

        // 获取用户的登录方式
        switch (userData.getLoginType()) {
            // 微信登录
            case USER_WX:
                token = wxAuthenticationService.code2Session(userData.getAccount());
                break;
            case USER_Email:
                break;
            default:
                throw new NotFoundException(10003);
        }
        map.put("token", token);
        return map;
    }

    /* 给前端校验token用,判断令牌是否合法 */
    @PostMapping("/verify")
    public Map<String, Boolean> verify(@RequestBody TokenDTO token) {
        Map<String, Boolean> map = new HashMap<>();
        Boolean valid = JwtToken.verifyToken(token.getToken());
        map.put("is_valid", valid);
        return map;
    }
}
