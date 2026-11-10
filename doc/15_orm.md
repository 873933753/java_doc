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

## 写接口步骤
0. Controller中注入Service层，调用Service层的业务（Service接口）
1. Service业务层（ServiceImpl）注入 Repository，实现业务逻辑
2. Repository接口（操作数据库），继承 JpaRepository<第一个参数是要操作的实体，第二个参数是实体的主键id类型>
3. Model层模型映射
- 导入SQL -> Model
- Entity实体
- jackson格式 
4. 接口判空返回code码


# theme - 主题（首页的各个模块）
- 数据库扩展手段：表的列 ，不具备扩展性。行就被扩展性。可考虑config表

## theme表和spu表是多对多的关系
- theme 主体表，spu商品表

## spu表
- price 文本类型，前端展示的价格。字段没有确定的意义，文本最方便，可转数字类型

### spu接口(类上才打@Service和@Repository)
- spu列表和详情
- 返回类型是实体决定的，如online表里是1，但是实体类型改成Boolean返回的是true

`详情接口：`
1. 导入spu表的SQL -> Entity加上Getter和Setter
2. 详情接口 - 
1) 新建spuController - getDetail /id/{id}/detail，id简单验证@Positive,作用类上要加@Validated
```java
@RestController
@RequestMapping("/spu")
```
2) 新建spuService接口及实现（@Service要打在实现类上）
3) 新建SpuRepository接口 继承 JpaRepository<第一个参数是要操作的实体Spu，第二个参数是实体的主键id类型>
- 定义findOneById,Service中注入SpuRepository并调用该方法
4) spuController中注册spuService，调用spuService的方法 getSpu，判空code码 30003，该 商品不存在

目前数据：
```json
{
    "id": 2,
    "title": "针织衫",
    "subtitle": "秋日红框，浪漫满屋",
    "categoryId": 12,
    "rootCategoryId": 2,
    "online": true,
    "price": "77.00",
    "sketchSpecId": 1,
    "defaultSkuId": 2,
    "img": "xxx",
    "discountPrice": "62",
    "description": null,
    "tags": "秋日冬款$浪漫满屋",
    "isTest": false,
    "forThemeImg": "yyy"
}
```

- 缺少数据
顶部轮播图，详情图，优惠券，sku
1. 轮播图与spu是 1对多的关系，新建spu_img 实体
2. 详情图与spu，1对多，新建spu_detail_img 实体
3. spu与sku，1对多，sku里很复杂，但是先只考虑spu和sku的关系（延迟思考，逐层深入），先新建sku实体

## 如何配置导航关系
- spuModel需要SpuDetailImg -> 在SpuModel中配置导航关系（即通过spu查详情，而不需要通过详情图片查spu）
- 即在SpuModel中配置对SpuImg SpuDetailImg Sku的关系，并指定外键名

`配置导航属性后： detail接口` 
- 会返回配置的导航属性
```json
{
    "id": 2,
    "title": "针织衫",
    "subtitle": "秋日红框，浪漫满屋",
    "categoryId": 12,
    "rootCategoryId": 2,
    "online": true,
    "price": "77.00",
    "sketchSpecId": 1,
    "defaultSkuId": 2,
    "img": "xxx",
    "discountPrice": "62",
    "description": null,
    "tags": "秋日冬款$浪漫满屋",
    "isTest": false,
    "forThemeImg": "yyy",
    "spuImgs": [
        {
            "id": 139,
            "img": "xss",
            "spuId": 2
        }
    ],
    "spuDetailImgs": [
        {
            "id": 138,
            "img": "xxx",
            "spuId": 2,
            "index": 0
        },
        {
            "id": 139,
            "img": "aaa",
            "spuId": 2,
            "index": 1
        }
    ],
    "skus": [
        {
            "id": 2,
            "price": 77.76,
            "discountPrice": null,
            "online": true,
            "img": "xxx",
            "title": "七龙珠",
            "spuId": 2,
            "specs": null,
            "code": "1$1-1#2-5",
            "stock": 0,
            "categoryId": 28,
            "rootCategoryId": 27
        }
    ]
}
```


`问题： `
此时的列表接口数据：
- 但是很多属性列表接口不需要。只需要部分的基础数据，如何精简数据？ -- 视图层 VO
- 性能问题。先查Spu表，再去查Spu Detail Image表，再查Spu Image表，再查SKU表，如何优化？

```json
[
    {
        "id": 2,
        "title": "针织衫",
        "subtitle": "秋日红框，浪漫满屋",
        "categoryId": 12,
        "rootCategoryId": 2,
        "online": true,
        "price": "77.00",
        "sketchSpecId": 1,
        "defaultSkuId": 2,
        "img": "xxx",
        "discountPrice": "62",
        "description": null,
        "tags": "秋日冬款$浪漫满屋",
        "isTest": false,
        "forThemeImg": "yyy",
        "spuImgs": [
            {
                "id": 139,
                "img": "xss",
                "spuId": 2
            }
        ],
        "spuDetailImgs": [
            {
                "id": 138,
                "img": "xxx",
                "spuId": 2,
                "index": 0
            },
            {
                "id": 139,
                "img": "aaa",
                "spuId": 2,
                "index": 1
            }
        ],
        "skus": [
            {
                "id": 2,
                "price": 77.76,
                "discountPrice": null,
                "online": true,
                "img": "xxx",
                "title": "七龙珠",
                "spuId": 2,
                "specs": null,
                "code": "1$1-1#2-5",
                "stock": 0,
                "categoryId": 28,
                "rootCategoryId": 27
            }
        ]
    }
]
```


`列表接口：（具有代表性） /latest`
## 但是很多属性列表接口不需要。只需要部分的基础数据，如何精简数据？ -- 视图层 VO
- 新建子包vo(view object)视图对象，dto定义的是接收数据的对象（作数据校验），vo是服务端返回前端的对象
- 新建SpuSimplifyVO，里面写前端需要的属性





# SpingBoot中静态资源的访问
- 文件放在resources/static 目录下,改文件夹下课新建任意包
- 安装依赖，模板渲染（经测试，未安装依赖也可以访问图片）
```xml
<dependency>
  <groupId>springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```


