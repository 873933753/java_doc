package com.tang.test;

public class A {
    /* 此时 A中是依赖 B 的，因为有new B的过程。*/
    private B b;
    public void print() {
        B b = new B();
        b.print();
    }

    // IOC思想 - 以前是我们向容器要对象（需要先new），现在是容器将对象给我们（直接用this.ic.xx）。所以这个过程是和之前相反的。
    // 这里是依赖注入 - 属性注入
    /* 这里的 A 不依赖C ，没有new C的过程，直接可以使用对象C */
    private IC ic;
    public void print2() {
        this.ic.print();
    }

    public void setIc(IC ic){
        this.ic = ic;
    }

    // 依赖注入 - 构造注入
    // 依赖通过构造器“强制”传入
    public  A(IC ic){
        this.ic = ic;
    }
}
