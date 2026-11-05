# 项目分层设计和JPA
## JPA 和 MyBatis
- 本质上是一套 ORM 规范，而非具体实现，都是简化数据库操作
- JPA 是“以对象为中心”的全自动 ORM 规范，而 MyBatis 是“以 SQL 为中心”的半自动映射框
- MyBatis 是什么：掌控 SQL 的半自动映射框架



# Service 层 - 写业务逻辑
- 使用@Service注解


## 层与层之间是否一定要用接口建立关联
- 比如Controller中调用Service层
- 简单的不用，复杂的经常修改的需要按照规范通过接口建立关联



# 创建数据库表的3种主要方式
- 首先安装mysql，mysql.com

1. 可视化管理工具（navicat） utf8m64,utf8m64_general_ci
2. SQL语句生成数据库表
3. Model 模型类映射

##  写数据库配置文件
- 都是用的Properties文件，Spring Boot是支持YML文件的，这里使用YML文件进行配置。好处是yml中可以用：分模块，不用一直点,就可以将项目中的Properties文件删除
- 优先使用YML有更好的层级结构。

1)  yml文件
- 注意冒号后面需要有空格
```yml
server:
  port: 8081

mysql:
  ip: 127.0.0.1
  port: 3306
```
`Properties文件格式`
```properties
server.port = 8081
hero.name = yase
hero.condition = anqila
```


## 多环境配置文件 - 分开发生产环境
1. config中创建 appliaction-dev.yml 和 application-prod.yml （如果有需要可以创建application-test.yml等）
2. appliaction.yml 在任何环境下都会生效，appliaction-dev.yml在DEV环境下生效，application-prod.yml在生产环境下生效
3. 激活指定环境
- 在appliaction.yml中指定
```yml
# 激活指定环境
spring:
  profiles:
    active: prod
```

- 开发环境是通过IDE启动项目的，生产环境没有IDE通过命令启动
> maven中 -> Run Maven Build 或者 maven命令行中输入 mvn clean package
target目录下生成打包好的jar包 - uber jar 超级jar包，不用tomcat服务器就可以启动，老的war包需要配合tomcat服务器才能启动
```cmd
// 启动命令并指定环境
java -jar xxx.jar --spring.profiles.active=dev
```


## 数据库配置
```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/missu?characterEncoding=utf-8&serverTimezone=GMT%2B8
    username: root
    password: 123456
```


## Model 模型类映射
### 1. 写模型类 model/Banner
```java
public class Banner {
    private long id;
    private String name;
    private String decscription;
    private String img;
    private String title;
}
```

### 将Banner的模型映射到数据库中
- Banner模型上打上注解 @Entity，表明是一个实体
- @Entity(实体)不是springboot自带的，是持久化库，需要安装jpa依赖.(alt+enter 快速导入)
- 安转依赖：mysql连接器和jdbc
- jpa的配置

> 启动程序 missu下会多一张banner的表

```java
@Entity
public class Banner {
    @Id // 给Entity打标识(主键)，javax.persistence的
    private long id;
    private String name;
    private String decscription;
    private String img;
    private String title;
}
```

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

`jpa配置`
```yml
spring:
  profiles:
    active: dev
  jpa:
    hibernate:
      ddl-auto: update 
```


## maven依赖安转失败原因自查
- 安装成功是在mavnen的依赖中可以看到，而不是有红色提示

1) 网络环境问题，更换数据源
2) 如何既没有红线又无代码报错，可能是缓存原因未实时更新 ---重启IDE
3) 检查是否关闭mavne的离线模式，离线会优先找本地的依赖（ctrl + alt + s）

