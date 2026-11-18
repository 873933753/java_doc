package com.tang.missu.dto.validators;


import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// ConstraintValidator<要校验的类，校验类的类型>
public class TokenPasswordValidator  implements ConstraintValidator<TokenPassword,String> {
    private Integer min;
    private Integer max;

    @Override
    public void initialize(TokenPassword constraintAnnotation) {
        // 获取设置的校验参数
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    // 校验传过来的参数，s则是password
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 先判空 - 工具类判空
        if (StringUtils.isEmpty(s)){
            return true; // 小程序code登录是没有password的，所以返回true
        }
        // 如果有传值则校验长度
        return s.length() >= this.min && s.length() <= this.max;
    }
}
