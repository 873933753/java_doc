# 定义
`接口可以规定一个方法的签名，但是不包含该方法的实现`

# 一些基本说明
1. 占一个java文件
2. 接口中的所有方法都默认是`public abstract`的抽象方法（没有实现，只有签名）；
**基本示例**
```java
  // 定义一个名为SimpleInterface的接口
  interface SimpleInterface {
      // 接口中可以声明多个接口方法
      // 接口中的方法类似抽象方法,没有实现,只有签名
      void doSomething();
      void doOtherThing();
      // 可以有返回值,也可以有参数
      int calculator(int x, int y);

      // 声明静态变量
      // 以下两种定义方式完全等价
      public static final double VERSION = 3.0;
      String PROTOCOL = "USB"; // 自动是public static final，公共的、静态的常量
  }
```

# 实现interface
- 类可以用关键字`implements`实现一个多个接口
- 类必须为interface中所有的抽象方法提供具体实现
```java
interface Readable {
    public int read(byte[] buffer);
}

interface Writable {
    int write(byte[] data);
}

// 一个类可以实现多个接口
class HardDisk implements Readable, Writable {

    // 必须实现Readable的抽象方法
    @Override
    public int read(byte[] buffer) {
        System.out.println("Reading data from HardDisk...");
        return buffer.length;
    }

    // 必须实现Writable的抽象方法
    @Override
    public void write(byte[] data) {
        System.out.println("Writing data to HardDisk...");
    }
}
```

# 接口的使用
可以把interface看做是一个类的父类(仅限于在使用方式上很像)。
```java
// 定义接口
public interface Animal {
    void makeSound();
}

// 实现接口
public class Dog implements Animal {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void makeSound() {
        System.out.println(name + "说: 汪汪!");
    }

    // 自有方法
    public void breathe() {
        System.out.println("呼吸空气中...");
    }
}

// 使用接口
public class Main {
    public static void main(String[] args) {
        // 使用接口类型声明变量
        // new Dog()却赋值给了Animal类型。是的,接口也可以作为一个类型。这其实也是一种"多态":
        Animal myDog = new Dog("Buddy");
        myDog.makeSound(); // 输出: Buddy说: 汪汪!
    }
}
```

# 