# ORM-对象关系映射 Object Relation Mapping
- 不是一种技术，是一种思想
- 用面向对象的方式看待数据库（数据库，表，记录-一行，字段）
> 关系对应：
表 - 记录 - 字段
类 - 对象 - 属性/成员变量


## Model 生成表 - 不推荐
- ORM 强调的是操作数据库，并不一定要用 Model 生成数据表，Model的作用也不是只生成表，用Model代表数据表进行增删改查很方便
- 推荐用：数据库设计工具生成（如navicat）或者 SQL
> 表 可以 生成 SQL
表 也可以生成 Model


1. navicat-模型
2. 表 - 导出为表，右键转储SQL文件
sql文件导入为表 - 选择数据库，右键运行SQL文件


# 项目开发流程
- 优先开发 CMS（后台管理） -> C 端 （小程序）
- 关闭模型生成表 ，配置文件ddl-auto: none
- 有了数据库表，如何逆向生成 Model

## 实现接口 /v1/banner/name/{name}
所有表的有的三个字段，设计表的时候（datetime）；
抽出成基类；
不需要返回给前端如何处理？（不进行序列化）；
基类不能打上@Entity注解（一个实体一张表），但是数据库字段未映射，如何处理？（打上注解@MappedSuperClass-表明它是Entity的一个父类）
序列化，安装第三方库 jackson（默认使用）；fastjson
不需要返回给前端的字段 - 打上注解@JsonIgnore（但是不够灵活，有的需要返回有的不需要返回则不能这样处理）

create_time.CURRENT_TIME - 不勾选更新
update_time.CURRENT_TIME-勾选更新
delete_time.NULL-不勾选

抽象类和Interface的不同？





## SQL -> Model
1. 数据库先点击运行SQL，生成表
2. IDE -> View -> Tool Window -> DataBae -> 测试连接
3. project structure -> 转JPA hi
4. view -> Tool Window -> Persistence 里面会列出所有Entity的实体 -> 右键missu -> Generate Persistence Mapping -> By DataBase Schema
下拉选择数据库数据源 -> Package放model文件夹下 -> 勾选需要生成Model的表 -> Ok

> 新版，在 DataBase中找到要导出Model的表，右键 -> Create JPA Entities from DB

