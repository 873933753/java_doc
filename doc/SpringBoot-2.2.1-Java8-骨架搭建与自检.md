# Spring Boot 2.2.1.RELEASE + Java 8 骨架搭建记录（自检用）

> 建立时间：2026-10-28
> 对应项目：`E:\2026_01\my_github\java_project\new_miss\missu`（artifactId `missu`）
> 用途：下次再搭同类工程，照本文走一遍；出问题时先查第五节排错表。

---

## 一、为什么走"4.x 骨架改回去"这条路

`start.spring.io` 现在只提供 4.0.0 及以上的版本。实测强行指定老版本会被拒：

```
GET https://start.spring.io/starter.zip?bootVersion=2.2.1.RELEASE
-> 400  "Invalid Spring Boot version '2.2.1.RELEASE', Spring Boot compatibility range is >=4.0.0"
```

其他几条路也堵了：

| 路径 | 状态 |
|---|---|
| start.spring.io / IDEA 内置向导 | ❌ 最低 4.0.0 |
| start.aliyun.com | ❌ 已 302 到 error.taobao.com |
| `spring init` CLI | ❌ 打的是同一个接口 |
| 手写全部文件 | ✅ 可行，但要敲 5~6 个文件 |
| **用 4.x 空骨架改 4 处** | ✅ **本文采用，实测 BUILD SUCCESS** |

关键前提：**改的时候项目里还一行业务代码都没有**。空骨架改版本是零成本；等写了代码再改，`jakarta.*` 之类会满地找牙。

---

## 二、环境前置（必须先确认）

| 项 | 值 |
|---|---|
| JDK 8 | `E:\soft\java\jdk-8u504` |
| JDK 21（系统默认） | `E:\soft\java\jdk-21.0.12.1` |
| Maven | `E:\soft\java\apache-maven-3.9.16` |
| Maven 本地仓库 | `E:\soft\java\maven-repo`（settings.xml 指定） |
| settings.xml | `C:\Users\tang\.m2\settings.xml`（阿里云 https 镜像） |
| IDEA | 2025.3.6.1，自带 Maven 3.9.11 |

**Spring Boot 2.2.1 不能在 JDK 17/21 上跑**，必须先切到 8：

1. 双击 `E:\soft\java\use-java8.cmd`（只影响这个窗口，不动系统环境变量）
2. 窗口里确认：

```
java -version        -> openjdk version "1.8.0_504"
mvn -v               -> 最后一行为 Java version: 1.8.0_504
```

`mvn` 报"不是内部或外部命令"时，当前窗口补一行（原因见第五节第 1 条）：

```
set "PATH=E:\soft\java\apache-maven-3.9.16\bin;%PATH%"
```

---

## 三、搭建步骤

### 3.1 用 IDEA 生成 4.x 空骨架

`File → New → Project →` 左侧选 **Spring Boot**（旧版叫 Spring Initializr）

| 字段 | 填什么 |
|---|---|
| Name | missu |
| Group | com.tang |
| Artifact | missu |
| Type | Maven |
| Language | Java |
| Packaging | Jar |
| Java | 17（这里的值只影响生成，等下要改） |
| Spring Boot | 4.1.2（默认即可，别动） |
| Dependencies | 只勾 **Spring Web** |

生成后目录应该只有这些文件，没有任何业务代码：

```
pom.xml
mvnw / mvnw.cmd / .mvn/wrapper/maven-wrapper.properties
src/main/java/com/tang/MissuApplication.java
src/main/resources/application.properties
src/test/java/com/tang/MissuApplicationTests.java
src/main/resources/static/  templates/  .gitignore  .gitattributes
```

> 若 IDEA 下载失败，用浏览器打开 https://start.spring.io 用同样参数生成 zip，解压后在 IDEA 里 Open。

### 3.2 改 pom.xml：四处 + 删空标签

| # | 位置 | Boot 4 骨架原值 | 改成 |
|---|---|---|---|
| 1 | `<parent><version>` | `4.1.2.RELEASE` | `2.2.1.RELEASE` |
| 2 | `<java.version>` | `17` | `1.8` |
| 3 | 依赖 artifactId | `spring-boot-starter-webmvc` | `spring-boot-starter-web` |
| 4 | 测试依赖 artifactId | `spring-boot-starter-webmvc-test` | `spring-boot-starter-test` |

第 3、4 条不能省：`spring-boot-starter-webmvc` 是 Boot 4 才有的新名字，2.2.1 里这个坐标不存在（Maven Central 上 2.2.1 是 404，4.0.0 才有）。

顺带删掉 Boot 4 模板带来的空标签（2.2 的模型校验会拿它们当噪音）：

```xml
<name/>  <description/>  <url/>
<licenses><license/></licenses>
<developers><developer/></developers>
<scm>...</scm>
```

改完的 pom.xml 应该长这样（可直接对照）：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.1.RELEASE</version>
        <relativePath/>
    </parent>
    <groupId>com.tang</groupId>
    <artifactId>missu</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

两个依赖和插件**都不写版本号**，由父 POM 统一管理，这才是继承 parent 的意义。

### 3.3 把 IDEA 掰到 JDK 8 和系统 Maven

漏了这步，后面全是假报错。

- `File → Project Structure → Project`：SDK 选 8，Language level 选 8
- `Project Structure → Modules`：模块 SDK 也是 8
- `Settings → Build, Execution, Deployment → Build Tools → Maven`：
  - **Maven home path**：`E:\soft\java\apache-maven-3.9.16`（**不要用 Wrapper**，见第五节第 3 条）
  - User settings file：勾 Override → `C:\Users\tang\.m2\settings.xml`
  - Local repository：勾 Override → 应显示 `E:\soft\java\maven-repo`
- `Settings → Build → Compiler → Java Compiler`：Project/Target bytecode version 都填 8
- `Maven → Runner → JRE`：选 8

### 3.4 重新导入

右侧 Maven 面板 → 左上角循环箭头图标，或右键 pom → `Maven → Sync Project`
（IDEA 2024 之后把 "Reload All Maven Projects" 改名叫 Sync Project，别找错）

### 3.5 命令行验证（别只信 IDEA 的绿勾）

在 JDK 8 窗口里：

```
cd /d E:\2026_01\my_github\java_project\new_miss\missu     :: cmd 跨盘必须带 /d
mvn clean package
dir target\*.jar
java -jar target\missu-0.0.1-SNAPSHOT.jar
```

### 3.6 加个 controller 验证包扫描

`src/main/java/com/tang/web/HelloController.java`：

```java
package com.tang.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "ok, java " + System.getProperty("java.version");
    }
}
```

包必须在 `com.tang` 之下——`@SpringBootApplication` 只扫自己的子树，放到 `com.other` 就扫不到。

---

## 四、自检清单（下次逐项打勾）

### 4.1 改造是否正确

- [ ] `mvn -v` 显示 `Java version: 1.8.0_504`
- [ ] pom 里 parent 版本是 `2.2.1.RELEASE`
- [ ] pom 里 `<java.version>` 是 `1.8`
- [ ] 依赖是 `spring-boot-starter-web`，**不是** `...-webmvc`
- [ ] 测试依赖是 `spring-boot-starter-test`，**不是** `...-webmvc-test`
- [ ] `spring-boot-maven-plugin` 还在，且没写版本号
- [ ] 空标签 `<name/> <licenses> <developers> <scm>` 已清掉
- [ ] Maven 面板里 missu 下面能看到 `Lifecycle` **和** `Dependencies`、`Plugins`

### 4.2 构建是否成功

`mvn clean package` 输出里必须出现：

```
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
--- spring-boot:2.2.1.RELEASE:repackage (repackage) @ missu ---
Replacing main artifact with repackaged archive
BUILD SUCCESS
```

- [ ] 有 `spring-boot:2.2.1.RELEASE:repackage`（说明 parent 生效了）
- [ ] 有 `Replacing main artifact`（说明打的是 fat jar，不是薄 jar）
- [ ] `target\missu-0.0.1-SNAPSHOT.jar` 约 **17MB**（十来 KB = repackage 没跑）

### 4.3 运行是否正常

`java -jar` 日志里必须出现：

```
Tomcat started on port(s): 8080 (http)
Started MissuApplication in x.xxx seconds
```

- [ ] 8080 正常监听
- [ ] 访问 `/hello` 返回内容里带 `java 1.8.0_504`
- [ ] 没加 controller 时访问 `/` 是 404 —— 这是**正常**的

### 4.4 界面标记怎么看

- 行号旁蓝色小图标 = 该 `<dependency>` / `<plugin>` 解析成功，可跳转（2 依赖 + 1 插件 = 3 个，正常）
- 黄色虚线 = weak warning，不拦构建（多为"版本由父 POM 管理"之类的提示）
- 黄色灯泡 = 有 Quick Fix，Alt+Enter 查看
- **只有红色才是错误**

---

## 五、排错对照表（本次实际踩到的）

### 1. `mvn` 不是内部或外部命令，但 PowerShell 里能用

**原因**：注册表 PATH 里写的是 `%MAVEN_HOME%\bin`，是引用不是字面路径。进程的环境块在启动那一刻定格；从旧终端（或 Windows Terminal 的旧进程）派生的窗口带着旧块，里面没有 `MAVEN_HOME`，这一项就成了死路径。

**解**：

```
set "PATH=E:\soft\java\apache-maven-3.9.16\bin;%PATH%"      :: 当前窗口临时生效
```

根治：关掉所有 Windows Terminal 窗口（任务管理器确认 WindowsTerminal.exe 也没了）再重开；或把上面这行写进 `use-java8.cmd`。

### 2. `cd E:\...` 敲了没反应，提示符不动，也不报错

**原因**：cmd.exe 每个盘各记一个当前目录。`cd E:\path` 只改 E 盘的记忆目录，人还留在 C 盘。

**解**：`cd /d E:\path`，或者先 `E:` 再 `cd \path`。PowerShell 无此问题。

### 3. `Guice/MissingImplementation: No implementation for ArtifactHandlerManager was bound`

**原因**：IDEA 项目设置 `mavenHomeTypeForPersistence=WRAPPER`，走 Maven Wrapper；Boot 4 骨架的 `.mvn/wrapper/maven-wrapper.properties` 指向 **apache-maven 3.10.0**，IDEA 下载后用它驱动自己的 maven-server，而 maven-server 是按 **3.9.11** 编的，模块绑定对不上就报这个。

**判断依据**：
- `.idea/workspace.xml` 里 `mavenHomeTypeForPersistence = "WRAPPER"`
- `C:\Users\tang\.m2\wrapper\dists\apache-maven-3.10.0-bin` 确实下载了
- `E:\soft\IntelliJ IDEA 2025.3.6.1\plugins\maven\lib\maven3\lib\maven-core-3.9.11.jar`

**解**：Maven home path 改成 `E:\soft\java\apache-maven-3.9.16`（或 Bundled 3.9.11），别用 Wrapper。

### 4. pom 顶部红点，Maven 面板只有 Lifecycle / Repositories，没有 Dependencies

**原因**：Maven 模型导入失败或没跑完。**先看真错误再点 Sync**，否则 Sync 一百次也没用。

**解**：点编辑器右上角红色错误数，或翻 Build 面板最开头几行；同时核对 IDEA 用的 settings.xml 与本地仓库路径跟命令行一致，`Work offline` 千万别勾。

### 5. `Unsupported class file major version 52 / 65`

**原因**：编译与运行用的 JDK 不一致（52=Java 8，65=Java 21）。Spring Boot 2.2.1 在 21 上起不来。

**解**：JAVA_HOME、IDEA Project SDK、Maven Runner JRE、Compiler bytecode 四处都对齐到 8。

### 6. `No main manifest attribute` / jar 只有十几 KB

**原因**：`spring-boot-maven-plugin` 被删了或没执行 repackage。

**解**：补回插件段（不写版本号），重新 `mvn clean package`。

### 7. 服务起来了但访问 404

**原因**：controller 不在 `@SpringBootApplication` 所在包（及其子包）之下。

**解**：把 controller 放到 `com.tang` 或它的子包。

### 8. `invalid target release: 1.8`

**原因**：编译用的 javac 版本低于 pom 里声明的目标版本（比如用 JDK 8 之前的编译器）。

**解**：确认 javac 在 8 以上、JAVA_HOME 指向 1.8+。

### 9. WSL 里用 Windows 的 java.exe 报 `Unable to access jarfile`

**原因**：Windows 版 java.exe 不认 `/mnt/e/...` 这种 WSL 路径。

**解**：转成 `E:\...` 传给它（`wslpath -w` 一行搞定），或先在目录里 cd 再敲相对路径。收尾 kill 进程时按日志里的 `with PID xxxx` 用 `taskkill /F /PID` 杀，别指望 kill WSL 的 job pid 能带走它。

---

## 六、Boot 2.2.1 的世界与 4.x 的差别（写代码时的红线）

改造完 pom 只是第一步，脑子也要切到 2.2 时代：

| 项 | 2.2.1（你的版本） | 4.x |
|---|---|---|
| Servlet 包名 | `javax.servlet.*` | `jakarta.servlet.*` |
| HTTP 客户端 | `RestTemplate` | `RestClient` / `@HttpExchange` |
| 错误响应 | 自己写 `@ControllerAdvice` | `ProblemDetail` |
| Spring Security | 5.x 写法（`WebSecurityConfigurerAdapter`） | 6.x/7.x 写法（`SecurityFilterChain` Bean） |
| 测试 starter | `spring-boot-starter-test` | `spring-boot-starter-*-test`（拆分过） |

**IDEA 的代码补全会推 4.x 的 API，它会骗你。** 看到 `jakarta` 就警觉。

另外：不要在 4.x 骨架里先写业务代码再改版本，改动成本会指数上升。

---

## 七、本次实际结果（可作为对照基线）

| 项 | 实测值 |
|---|---|
| 构建命令 | `mvn clean package`（JDK 8 + Maven 3.9.16） |
| 结果 | `BUILD SUCCESS`，7.583 s |
| 测试 | `Tests run: 1, Failures: 0, Errors: 0` |
| repackage | `spring-boot:2.2.1.RELEASE:repackage` 已执行 |
| 产物 | `target/missu-0.0.1-SNAPSHOT.jar`，约 17MB，内含 37 个依赖 jar |
| 启动 | `Started MissuApplication in 1.929 seconds`，Tomcat 9.0.27 @ 8080 |
| 接口验证 | `/hello` 返回 `ok, java 1.8.0_504` |

> 参照工程：`E:\2026_01\my_github\logic\java\missyou`（artifactId `missyou`，同样 2.2.1 + Java 8）

---

## 八、下次开工最短路径

```
1. 双击 use-java8.cmd，java -version / mvn -v 都确认 1.8.0_504
2. IDEA 建 4.1.2 + Spring Web 骨架
3. pom 改四处（parent 版本、java.version、web、test）+ 删空标签
4. IDEA：Project SDK 8 / Maven home 用 3.9.16 不用 Wrapper / Compiler bytecode 8
5. Sync Project
6. 命令行 mvn clean package → 17MB jar → java -jar → 启动日志 → /hello
```

六步走完还没成，直接翻第五节。
