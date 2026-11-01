# 工厂模式
- 核心：把**实例化对象（创建对象）**这件事从代码中抽出来，交给一个专门的类或方法负责

# 形式1: 简单工厂
# 形式2: 工厂方法
# 形式3: 抽象工厂



# 反射
- 是什么？反射让你在看不到源代码的情况下，仅仅通过一个类的全限定名（字符串），就能了解这个类的所有内部结构，并能创建它的对象、调用它的方法、访问它的字段。 
- 注解是反射机制实现的
```text
    正常方式：你知道类名 → import → new → 使用
    反射方式：你只有字符串 → Class.forName → 动态创建 → 使用
```



# IOC,DI,DIP.概念

## DIP - 依赖倒置（Dependency Inversion Principle）
- 高层模块（抽象）不应该依赖低层模块，两者都应该依赖抽象。
- 抽象不应该依赖细节。
- 细节应该依赖抽象。


## DI - 依赖注入(Dependency Injection)
- 对象之间的相互作用，产生依赖。
- 容器将对象注入进去（A和C对象之间有依赖，不再new C，而是容器将对象C注入到容器A中，这样也产生了依赖，但是依赖的形式是注入的，所以叫**依赖注入**）
- 容器的作用是装配对象

### 依赖注入的几种主要形式
1. 属性注入（Setter 方法注入）
- 通过类的 setter 方法传入依赖，容器先调用无参构造创建对象，再调用 setter 完成注入。
```java
public class A {
    private IC ic; // 面向抽象，对接口编程
    public void print2() {
        this.ic.print();
    }
    // 需要调用setter注入对象
    public void setIc(IC ic){
        this.ic = ic;
    }
}

// 调用的地方
IC ic = new C();
A a = new A();
a.setIc(ic)
```

2. 构造注入 - 通过类的构造函数传入依赖。这是 Spring 官方首推的方式。
```java
// 依赖注入 - 构造注入
    // 依赖通过构造器“强制”传入
    public  A(IC ic){
        this.ic = ic;
    }

    // 调用的地方
    IC ic = new C();
    A a = new A(ic);

```

3. 接口注入（用的少，主要是以上两种）


## IOC 控制反转 (Inversion Of Controll)
- 以前是我们向容器要对象，现在是容器将对象给我们。所以这个过程是和之前相反的。

IOC 实现：容器；加入容器（主要难点）；注入
### 加入容器的方式
1. XML - 现在用的不多
2. 注解 - SpringBoot中

```java
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
```

```java
// 接口IC
interface IC { void doSomething(); }

// C和D 共同实现 IC
class C implements IC { public void doSomething() { ... } }
class D implements IC { public void doSomething() { ... } }

class A {
    private final IC ic;              // ① 依赖接口，不依赖 C

    public A(IC ic) {                 // ② 由外部把实现送进来 - 这里注入对象
        this.ic = ic;
    }

    public void run() {
        ic.doSomething();             // A 只知道 IC 有这个方法-A调用C的方法
    }
}
```
- 写 A 的人：只看 IC，不看 C / D 的实现。
- 装配时：容器 决定 new A(new C()) 还是 new A(new D())。
- A 的代码一行都不用改，就能换实现。






