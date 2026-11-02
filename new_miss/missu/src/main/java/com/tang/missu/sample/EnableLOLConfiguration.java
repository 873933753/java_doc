package com.tang.missu.sample;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/* 自定义 @EnableLOLConfiguration 注解，自定义注解使用@interface关键字来声明 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //元注解 - 用在注解上的注解，用于定义注解的基本特征
@Documented
@Import(HeroConfiguration.class)
public @interface EnableLOLConfiguration {
}
