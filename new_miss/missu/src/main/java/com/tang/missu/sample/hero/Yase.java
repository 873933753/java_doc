package com.tang.missu.sample.hero;

import com.tang.missu.sample.ISkill;

public class Yase implements ISkill {
    public Yase(String name) {
        System.out.println("hello:" + name);
    }
    public void skillA() {
        System.out.print("Yase-释放技能A");
    }

    public void skillB() {
        System.out.print("Yase-释放技能B");
    }

    public void skillC() {
        System.out.print("Yase-释放技能C");
    }
}
