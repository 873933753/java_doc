# 参数校验
- 代码不能写在控制器中，控制器中不能写业务逻辑

# 参数处理分两类
## 1. 通过url传递的 - query
### 1.1 写在路径中的，如/id/123
`如何接收参数？`
- 注解 @PathVariable接收
```java
@GetMapping("/test/params/{id}")
public String testParams(@PathVariable  Integer id){
    return "hi,hanber" + id;
}

// 可以指定参数名
@GetMapping("/test/params/{id1}")
public String testParams(@PathVariable(name = "id1")  Integer id){
    return "hi,hanber" + id;
}

/* 接收参数
* @PathVariable - 接收路径中的
* @RequestParam - 接收 ? 后的 query 参数
* */
@GetMapping("/test/params/{id1}")
public String testParams(@PathVariable(name = "id1")  Integer id,@RequestParam String name){
    return "hi,hanber" + id + name;
}
```

### 1.2 写在 ? 后的，如 ?id = 123
`如何接收参数？`
- @RequestParam - 接收 ? 后的 query 参数
```java
/* 接收参数
* @PathVariable - 接收路径中的
* @RequestParam - 接收 ? 后的 query 参数
* */
@GetMapping("/test/params/{id1}")
public String testParams(@PathVariable(name = "id1")  Integer id,@RequestParam String name){
    return "hi,hanber" + id + name;
}
```

## 2. 通过post请求传递的 - request body
- 既可以在url中传递参数，也可以在body中传递参数
- 注解@RequestBody接收参数，但是指定接收参数的类型


数据传输对象
- DTO 就是专门用来“搬数据”的简单对象，目的是解耦、裁剪字段、保证安全和适配接口契约。

# Lombok
- Lombok 是一个 Java 库，它通过注解在编译期自动生成样板代码，让你不用手写 getter、setter、构造方法
- 类中的getter，setter会使得里面的模板代码非常多，打上@Getter@Setter,Lombok会自动生成对应的

## maven安装Lombok
- 版本号可以不写，或者指定某个版本
```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
</dependency>
```

## @Getter和@Setter
- 会给bean生成getter和setter方法，但对final无效

### 与@Data注解的不同
- 除了有getter和setter方法，还会生成equals，hashCode，toString方法

## Lombok创建构造函数
### @AllArgsConstructor - 创建全参数的构造函数
- 自动生成有参的构造函数

### @NoArgsConstructor - 创建无参的构造函数

### @NonNull - 参数不为空

### @RequiredArgusConstructor - 创建非空参数的构造函数
