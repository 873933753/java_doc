package com.tang.missu.sample.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/* 条件类，需要实现Condition接口，springboot内置的 */
// 选择Springframework的
public class XiaoQiaoCondition implements Condition {
    // 需要实现接口必须实现它的方法，右键implements -> Select Methods to implements
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 这里返回true则会加入到IOC容器（可动态根据条件加入）
//        return true;
        String name = conditionContext.getEnvironment().getProperty("hero.name");
        return  "xiaoqiao".equalsIgnoreCase(name);
    }
}
