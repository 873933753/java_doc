package com.tang.missu.sample;

import com.tang.missu.sample.condition.XiaoQiaoCondition;
import com.tang.missu.sample.condition.YaseCondition;
import com.tang.missu.sample.hero.Anqila;
import com.tang.missu.sample.hero.XiaoQiao;
import com.tang.missu.sample.hero.Yase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


/* 配置类 */
@Configuration
public class HeroConfiguration {
    @Bean
    @Conditional(XiaoQiaoCondition.class) //将条件类传入到Conditional注解中
    public ISkill xiaoqiao() {
        return new XiaoQiao("XiaoQiao");
    }

    @Bean
    @Conditional(YaseCondition.class)
    public ISkill yase() {
        return  new Yase("yase");
    }

    @Bean
    @ConditionalOnProperty(value = "hero.condition",havingValue = "anqila",matchIfMissing = true) // 不用再写条件类,matchIfMissing默认是false，如果找不到会报错，否则会给默认值Anqila类
    public ISkill anqila(){
        return new Anqila("安琪拉");
    }
}
