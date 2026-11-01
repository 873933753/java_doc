package com.tang.reflect;

import com.tang.reflect.hero.Daji;
import com.tang.reflect.hero.Diana;
import com.tang.reflect.hero.XiaoQiao;

// 工厂 + 反射 （并不是IOC和DI）
public class HeroFactory {
    public static SkillInterface getHero(String name) throws Exception{
        SkillInterface iSkill;
        // 思路 - 通过用户输入的字符串，反射成对应的对象，即用户输入"Daji",代码生成Daji这个对象
        // 即：根据用户输入的字符串，在运行时动态创建对应的英雄对象，而不需要在代码里写死"输入 Daji 就 new Daji()"这样的分支判断。

        String classStr = "com.tang.reflect.hero." + name; // 字符串拼接，需要引入对应包,完整包名
        Class<?> cla = Class.forName(classStr);
        Object obj = cla.newInstance(); // java8之后被废弃，新语法 cla.getDeclaredConstructor().newInstance();
        iSkill = (SkillInterface)obj;
        return iSkill;

        // 目前工厂中的代码是不稳定的，用户的输入会导致变化，用户的输入是造成不稳定的原因
        /*SkillInterface iSkill;
        switch (name){
            case "Diana":
                iSkill = new Diana();
                break;
            case "XiaoQiao":
                iSkill = new XiaoQiao();
                break;
            case "Daji":
                iSkill = new Daji();
                break;
            default:
                throw new Exception();
        }
        return iSkill; // 返回实例化的对象*/

    }
}
