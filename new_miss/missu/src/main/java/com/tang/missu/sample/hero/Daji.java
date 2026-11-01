package com.tang.missu.sample.hero;

import com.tang.missu.sample.ISkill;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component // @Component注解 - 会将类添加到容器中，容器负责类的实例化
//@Lazy // 延迟实例化
public class Daji implements ISkill {
    public Daji() {
        System.out.println("hello,Daji");
    }
    public void skillA() {
        System.out.print("Daji-释放技能A");
    }

    public void skillB() {
        System.out.print("Daji-释放技能B");
    }

    public void skillC() {
        System.out.print("Daji-释放技能C");
    }
}
