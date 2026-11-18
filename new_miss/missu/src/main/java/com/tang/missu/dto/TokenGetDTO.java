package com.tang.missu.dto;

import com.tang.missu.core.enumeration.LoginType;
import com.tang.missu.dto.validators.TokenPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenGetDTO {
    // account不能空，可以是xcx的code，xcx只有code
    @NotBlank(message = "account不允许为空")
    private String account;

    // 自定义注解验证 - password，可能是验证码，可能是密码
    // {token.password} 是配置项中配置的
    @TokenPassword(max=30, message = "{token.password}")
    private String password;

    // 登录类型 - 微信登录，手机密码登录，邮箱登录
    private LoginType loginType;
}
