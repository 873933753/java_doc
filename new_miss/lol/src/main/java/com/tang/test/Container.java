package com.tang.test;

// 1.容器 - 容器的作用是装配对象（有很多类，比如A和C之间有依赖关系）
// 比如A和C之间有依赖关系（A中调用了C的代码），写A时候可以完全不用考虑C的代码，。为何？
// 因为A中依赖的不是C这个具体对象，依赖的是接口（C和C可以共同实现IC接口），A只需要关心接口，接口是抽象的，接口被装配实例化哪一个对象并不关心
// 这样可以保证各个类之间完全独立
public class Container {
    public void getBean() {
        // 2. 加入容器（对象加入容器）
        IC ic = new C();

        // 3. 注入 - 将容器中的对象注入到代码中
        //   1）构造函数注入
        A a = new A(ic);
        //   2) 属性注入 -setter注入
        A a2 = new A();
        a2.setIc(ic);
    }
}
