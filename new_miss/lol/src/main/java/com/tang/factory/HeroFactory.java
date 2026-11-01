package com.tang.factory;

import com.tang.factory.hero.Daji;
import com.tang.factory.hero.Diana;
import com.tang.factory.hero.XiaoQiao;

// 将对象实例化的过程，移到工厂中
public class HeroFactory {
    public static SkillInterface getHero(String name) throws Exception{
        SkillInterface iSkill;
        // switch的主体是在将对象实例化
        // 目前工厂中的代码是不稳定的，用户的输入会导致变化，用户的输入是造成不稳定的原因
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
        return iSkill; // 返回实例化的对象
    }
}
