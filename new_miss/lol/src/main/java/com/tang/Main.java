package com.tang;

/*lower*/
//import com.tang.lower.hero.Diana;
//import com.tang.abstraction.SkillInterface;
import com.tang.abstraction.hero.Daji;
import com.tang.abstraction.hero.Diana;
import com.tang.abstraction.hero.XiaoQiao;
/*factory*/
//import com.tang.factory.HeroFactory;
//import com.tang.reflect.SkillInterface;
/*reflect*/
import com.tang.reflect.HeroFactory;
import com.tang.reflect.SkillInterface;
import com.tang.test.A;
import com.tang.test.IC;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/* 面向对象编程的手段
* interface
* 工厂模式
* ICO/DI
* 目的是： => 面向抽象 => OCP（开闭原则） => 可维护的代码
* */
public class Main {
    /*1.lower-直白方式写：每新增一个英雄就要写大量重复的代码*/
//    public static void main(String[] args) {
//        // 调用获取英雄名的方法
//        String name = Main.getPlayerInput();
//        /*if(name.equals("Diana")){
//            Diana diana = new Diana();
//            diana.skillA();
//        }else{
//            System.out.println("没有该英雄，请重新输入");
//        }*/
//
//        switch (name) {
//            case "Diana":
//                Diana diana = new Diana();
//                diana.skillA();
//                break;
//            case "Daji":
//                Daji daji = new Daji();
//                daji.skillA();
//                break;
//            case "XiaoQiao":
//                XiaoQiao xiaoQiao = new XiaoQiao();
//                xiaoQiao.skillA();
//                break;
//            default:
//                System.out.println("没有该英雄");
//        }
//    }

    /*2.interface - 目前解决了方法统一调用的问题，没有解决对象统一示例化的问题*/
    /*重点理论：
     1.单纯的interface可以统一方法的调用，但是不能统一对象的实例化
       如：以下每个英雄都要new，但是可以统一用iSkill.skillA();调用方法
     2. 面向对象主要做两件事：实例化对象（单纯的interface已经可以做到）；方法调用（完成业务逻辑）
     3.只有一段代码中没有new出现，才能保持代码的相对稳定，才能逐步实现OCP
      （实质是一段代码如果要保持稳定，就不应该负责对象的实例化）
     4. 对象的实例化是不可能消除的，只能将对象的实例化过程，转移到其他的代码片段中
    */
//    public static void main(String[] args) {
//        String name = Main.getPlayerInput();
//        SkillInterface iSkill;
//        // switch的主体是在将对象实例化
//        switch (name){
//            case "Diana":
//                iSkill = new Diana();
//                break;
//            case "XiaoQiao":
//                iSkill = new XiaoQiao();
//                break;
//            case "Daji":
//                iSkill = new Daji();
//                break;
//            default:
//                System.out.println("没有该英雄");
//                return; //终止流程，避免走到下面的 skillA(),否则iSkill未被初始化
//        }
//        // 调用interface上的方法
//        iSkill.skillA();
//    }

    /*3.工厂模式(目前是简单工厂) - 将实例化对象的过程移到工厂模式中*/
    // 目前main函数实现了OCP（即目前main中的代码是相对稳定的） - 新增英雄main方法不用改，但是工厂模式中的代码不是稳定的（用户的输入会导致变化，用户的输入是造成不稳定的原因）
//    public static void main(String[] args) throws Exception{
//        String name = Main.getPlayerInput();
//        // 从工厂获取实例化的对象
//        SkillInterface iSkill = HeroFactory.getHero(name);
//        iSkill.skillA();
//    }

    public static void main(String[] args) {
        A a = new A();
        a.print2();
    }


    // 获取用户输入的英雄名
    private static String getPlayerInput() {
        System.out.println("请输入英雄名：");
        // 获取输入
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine(); // 返回输入
    }
}