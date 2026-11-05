# 参数校验
- 代码不能写在控制器中，控制器中不能写业务逻辑

# 1. 参数处理分两类
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
```java
// PersonDTO
/* DTO - 数据传输对象，处理接口参数的 */
public class PersonDTO {
    private String name;
    private Integer age;


    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}

// @RequestBody接收参数
@PostMapping("/test/post")
public String testPost(@RequestBody PersonDTO person){
    return "hi" + "name:" + person.getName() + " age:" + person.getAge();
}
```



# 2. Lombok
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
- 不用再写getAge，setAge
![lombok_getter](assets/validate/lombok_getter.png)

### 与@Data注解的不同
- 除了有getter和setter方法，还会生成equals，hashCode，toString方法


## Lombok创建构造函数
1. @AllArgsConstructor - 创建全参数的构造函数
- 自动生成有参的构造函数

2. @NoArgsConstructor - 创建无参的构造函数

3. @RequiredArgusConstructor + @NonNull - 创建非空参数的构造函数
![lombok_constructor](assets/validate/lombok_constructor.png)

## @Builder - 可以直接给成员变量赋值
- 不用每个成员变量都写setter赋值
- 问题1：打上 @Builder之后就不能通过new的方法实例化对象，会报错。会默认使用builder的方法实例化对象
因为 @Builder 会生成私有的构造函数，不支持外部访问
解决办法：打上@NoArgsConstructor生成public的无参构造函数
- 问题2：用builder实例化的对象返回前端需要加@Getter

```java
PersonDTO dto = PersonDTO.builder()
                 .name("ber")
                 .age(3)
                 .build();
```



# 3. 参数校验
- 验证的代码不要写在控制器中
- 约束注解@Max要写在需要被校验单对象上, @Validated 贴在「谁的参数要校验」的类身上（@Validated 的开关只认类不是方法）,@Valid写在级联往下查的引用上

## 1.使用基础注解进行参数校验
- @Validate +  @Max，@Min，@Range，@Length，@Positive
- 要验证关联的对象（对象数据中还有对象），打上@Valid注解

`@Validate 和 @Valid 区别`
- 都可以开启校验，但是使用的功能上有差别
- 推荐：开启校验的时候用 @Validate，级联的时候用 @Valid
```java
/* BannerController中 */
//接收body参数
    // @Validated 开启校验
    @PostMapping("/test/post")
    public PersonDTO testPost(@RequestBody @Validated PersonDTO person){
        PersonDTO dto = new PersonDTO(person.getName(),person.getAge(),person.getSchoolDTO());
        return dto;
//          PersonDTO dto = PersonDTO.builder()
//                  .name("ber")
//                  .age(3)
//                  .build();
//          return dto;

    }

/* PersonDTO中 */
@Getter //lombok的Getter
//@Setter
@AllArgsConstructor //lombok - 生成全参的构造函数
@NoArgsConstructor // lombok - 生成无参的构造函数
//@RequiredArgsConstructor
//@Builder // lombok
public class PersonDTO {
    @NonNull // lombok - 创建name的构造函数
    @Length(min = 1,max = 3,message = "长度在1-3之间")
    private String name;
    @Max(value = 10,message = "最大不能超过10哦")
    private Integer age;

    @Valid // 需要@Valid，schoolDTO中的验证注解才会生效
    private SchoolDTO schoolDTO;
}

/* SchoolDTO中 */
@Getter
@Setter
public class SchoolDTO {
    @Length(min = 1,max = 2,message = "学校名字不超过2位哦")
    private String schoolName;

    @Range(min=1,max=3,message = "年级最大不超过3")
    private int classLevel;

}
```


## 自定义校验注解
- 自己实现一个 @PasswordEqual 注解，校验两次输入的密码是否相等 validators/PasswordEquals（Annotation）

- 元注解

- 自定义注解的关联类PasswordValidator，需要实现接口 ConstraintValidator<自定义注解类型，自定义注解所修饰的目标的类型>
如 @PasswordEqual 最终是打在 PersonDTO 上的，则 PersonDTO 就是 自定义注解所修饰的目标的类型（如果修饰的是字段String name，则目标类型是String），ConstraintValidator<@PasswordEqual, PersonDTO>
- 需要实现方法 isValid，具体的校验逻辑写在这里面

- **将自定义注解和它的关联类关联到一起**，自定义注解上打注解 @Constraint(validateBy=PasswordValidator.class) 即表明这个自定义注解具体是由哪个关联类进行验证的,可以指定多个关联类

### 1. 自定义注解@PasswordEqual

### 2. 自定义注解的关联类PasswordValidator

### 3. 将自定义注解和它的关联类关联到一起
### 4. 使用自定义注解
- 将自定义注解打在要验证的类上，验证生效需要打上 @Validated 注解

### 自定义注解传参数及获取参数
- 自定义注解中定义成员变量，如 int min() default 4;
- 获取使用注解中传入的值，在关联类中 覆盖方法 initialize拿到值



## 处理参数校验的异常
- 之前只处理了通用异常Exception和HttpException
- 处理的异常类型 MethodArgumentNotValidException（body中的参数），ConstraintViolationException（url中的参数）
```java
// body中的参数 异常处理
/* 处理已知异常 - 参数问题 */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) // 400
    public UnifyResponse hanldeArgmentException(HttpServletRequest req, MethodArgumentNotValidException e) {
        String requestUrl = req.getRequestURI(); // 获取请求接口名
        String method = req.getMethod(); // 获取请求类型
        System.out.println("xxx:"+e); // 开发时通过打印e查看异常原因
        // 有可能是多个错误
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = this.formatAllErrorMessages(errors);
        UnifyResponse UniMessage = new UnifyResponse(10001,message,method + " " +requestUrl);
        return UniMessage;
    }
    // 自定义方法处理错误,将多个错误拼接在一起
    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error -> errorMsg.append(error.getDefaultMessage()).append(';'));
        return errorMsg.toString();
    }
```
`@Validated 要写在验证注解的类上BannerController上`
```java
@GetMapping("/test/params/{id1}")
//    @Validated - 这里@Validated写在方法上无效
    public String testParams(@PathVariable(name = "id1") @Max(10) Integer id, @RequestParam @Length(min = 1,max = 2) String name){
        return "hi,hanber" + id + name;
    }

// url中参数异常的处理
// url上的参数异常- ConstraintViolationException
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public UnifyResponse handleConstrainException(HttpServletRequest req,ConstraintViolationException e){
        String requestUrl = req.getRequestURI(); // 获取请求接口名
        String method = req.getMethod(); // 获取请求类型
        String message = e.getMessage();

        UnifyResponse UniMessage = new UnifyResponse(10001,message,method + " " +requestUrl);
        return UniMessage;
    }
```


