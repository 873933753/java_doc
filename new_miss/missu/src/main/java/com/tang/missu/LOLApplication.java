package com.tang.missu;

import com.tang.missu.sample.EnableLOLConfiguration;
import com.tang.missu.sample.HeroConfiguration;
import com.tang.missu.sample.ISkill;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/* 手写启动类，不依赖@SpringBootApplication注解 */
//@ComponentScan
//@Import(HeroConfiguration.class)
@EnableLOLConfiguration
public class LOLApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(LOLApplication.class)
                .web(WebApplicationType.NONE) // 将web服务关掉
                .run(args);
        ISkill iSkill = (ISkill) context.getBean("yase");
        iSkill.skillA();
    }
}
