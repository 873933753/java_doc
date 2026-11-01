package com.tang.abstraction.hero;

import com.tang.abstraction.SkillInterface;

public class Diana implements SkillInterface {
    public void skillA() {
        System.out.print("Diana-释放技能A");
    }

    public void skillB() {
        System.out.print("Diana-释放技能B");
    }

    public void skillC() {
        System.out.print("Diana-释放技能C");
    }
}
