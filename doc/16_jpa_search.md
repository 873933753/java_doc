# JPA 的多种查询

# spu 列表接口：（具有代表性） /latest
- 列表的数据如何将一组数据赋给vo呢？安装依赖 dozermapper 处理List的属性拷贝
- dozermapper优势：可以进行深拷贝（如果下面有数组）

## 处理List的属性拷贝
```xml
<dependency>
    <groupId>com.github.dozermapper</groupId>
    <artifactId>dozer-core</artifactId>
    <version>6.5.0</version>
</dependency>
```
`接口处理：`
```java
@GetMapping("/latest")
  public List<SpuSimplifyVO> getLatestSpuList(){
      // 数据源
      List<Spu> spuList = this.spuService.getLatestPagingSpu();
      // 目标 -vo
      List<SpuSimplifyVO> vos = new ArrayList<>();
      // mapper
      Mapper mapper = DozerBeanMapperBuilder.buildDefault();
      // 将spuList中的各项拷贝到vos中
      spuList.forEach( spu -> {
          SpuSimplifyVO vo = mapper.map(spu,SpuSimplifyVO.class);
          vos.add(vo);
      });

      return vos;
  }
```
> 接口返回数据：
```json
[
    {
        "id": 2,
        "title": "针织衫",
        "subtitle": "秋日红框，浪漫满屋",
        "price": "77.00",
        "sketch_spec_id": 1,
        "default_sku_id": 2,
        "img": "xxx",
        "discount_price": "62",
        "description": null,
        "tags": "秋日冬款$浪漫满屋"
    }
]
```


## 分页
- 分页参数：pc端Page，size，移动端start，count；且参数非必须，需要有默认值


### BO（businsess） 业务对象，service向controller传输对象
- 前端传入的是start和count ，需要转换为 pageNum和size；即service向controller传start，需要经过BO处理为pageNum
- 新建bo包，创建PageCount类
- 封装方法

### VO 创建返回给前端的分页类（Paging）
- 封装Paging
```java
// 这里传入泛型，因为Paging是公共的封装，可能是Page<Spu> Page<Sku>
@Getter
@Setter
public class Paging<T> {
    // 分页数据基本结构
    private Integer page; // 当前页码
    private Integer count; // 条数
    private Integer totalPage;// 总页数
    private Long total; //总条数
    // 列表数据
    List<T> items;

    //返回给前端的数据处理
    public Paging(Page<T> pageT) {
        this.initPageParameters(pageT);
        this.items = pageT.getContent();
    }

    // 获取分页数据
    void initPageParameters(Page<T> pageT) {
        // 以下方法都是Page对象内置的方法
        this.page = pageT.getNumber();
        this.count = pageT.getSize();
        this.totalPage = pageT.getTotalPages();
        this.total = pageT.getTotalElements();
    }
}
```

- 使用的地方
![spu分页列表](assets/16/spu_paging_1.png)
此时的分页数据：
```json
[
    {
        "id": 2,
        "title": "针织衫",
        "subtitle": "秋日红框，浪漫满屋",
        "price": "77.00",
        "sketch_spec_id": 1,
        "default_sku_id": 2,
        "img": "xxx",
        "discount_price": "62",
        "description": null,
        "tags": "秋日冬款$浪漫满屋"
    },
    {
        "id": 3,
        "title": "t恤",
        "subtitle": "哈哈哈",
        "price": "57.00",
        "sketch_spec_id": 1,
        "default_sku_id": 2,
        "img": "xxx",
        "discount_price": "62",
        "description": null,
        "tags": "秋日冬款$浪漫满屋"
    }
]
```

> 问题：需要循环paging.getItems()，如何封装这里-抽一个VO PagingDozer继承Paging做属性的复制
```java
/* 如果不需要精简属性用Paging对象即可，如果需要精简属性需要使用PagingDozer */
/* T -代表源，K代表目标 */
public class PagingDozer<T,K> extends Paging {

//    @SuppressWarnings("unchecked") // 忽略警告
    public PagingDozer(Page<T> pageT,Class<K> classK) {
        // 调用Paging的基本属性赋值
        this.initPageParameters(pageT);
        // items属性复制 - Mapper
        List<T> items = pageT.getContent();
        // vo
        List<K> vos = new ArrayList<>();
        // mapper
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        // 将spuList中的各项拷贝到vos中
        items.forEach( t -> {
            // 目标源类不能直接K.class，通过构造函数传
            K vo = mapper.map(t,classK);
            vos.add(vo);
        });
        // 需要将数据set进去
        this.setItems(vos);
    }
}
```



# 分类表的设计 - 一张表
- index排序，is_root是否根节点，online是否下架，level几级分类（如果分类层级较多，level可以简化查询）


## 目前方式
节点，记住父级节点的id
> 缺点：不好回溯。即一个5级节点，知道第五级节点，需要一层层向上找才能找到一级节点


## 改进：路径表示法
- 记录分类的完整路径，新增一个path字段存完整的分类路径（牺牲了一定的数据库存储空间，但是可以提高查询效率）
- 两级分类推荐用第一种，如果分类层级较多或者无限级推荐路径表示法更好。


## 写api - 根据分类id查spu，/by/category/{id}
- 根据一级分类查spu列表
- 或者 根据二级分类查spu列表

1. 导入分类表
2. 使用JPA带条件查询（命名方式推导出的SQL语句）
3. 校验返回Message的处理。(可以截取前面不要的，不要返回给前端。)
```json

```




# sku关于规格字段的设计 sku_spec 表
`之前的sku表的字段：`
![sku表的字段](assets/16/sku_1.png)
- spec是属于sku的，之间是什么关系？ 多对多的关系（如果没有standard可以设计成1对多的关系）

## 规格相关概念
Spec_Key: 颜色；尺码
Spec_value：橙色，黑色；S，M，L，XL

1. Spec_Key 和 Spec_value 两个实体，两个表
1 对 多的关系 - 一个key会有多个value，通过specId关联

2. spec 和 spu是多对多的关系，中间表 spu_key（规格名和spu建立多对多的关系，外键 spec_key_id 和 spu_id）

3. 每个sku有不用颜色（规格值），即sku和spec_value是多对多的关系，中间表sku_spec
- sku_spec表的外键按理说是：sku_id 和 value_id （但是多设计了两个冗余字段，spu_id,key_id）

  
`sku 中的code`
- 唯一编码，相当一个协议，这个串码可以表达一个sku（15$1-19#6-28）

SpuId $ specKey1-specValue1  # specKey2 - specValue2
15$1-19#6-28


## 返回给前端json数据格式的处理
> 问题：skuModel中的specs是String类型，数据库中sepcs是json类型，返回前端是字符串类型的specs不好处理
 ![skuModel中的specs](assets/16/specs_0.png)
 ![返回给前端的specs](assets/16/specs_1.png)

 - 解决办法，定义一个spec的类返回给前端
 > 新的问题：
// 这里定义Spec类，但是实体中的成员类型要和数据库一致，specs在数据库中是json类型，而且其他地方也要每次定义类
// 想要实现一个共同的，且数组类型的映射和单一的Object类型的映射不一样

1. 单体JSON与Map的映射
- 目前新增一个字段test，返回给前端：
```json
"test": "{\"key\": \"尺寸\", \"value\": \"7英寸\", \"key_id\": 2, \"value_id\": 5}"
```
- AttributeConverter<当前实体当前字段类型，字段在数据库中的类型String>，java没有json类型

```java
// 转换器
/* 处理单体Json的映射 */
//@Convert // 转换器,需要配AttributeConverter，JPA 在存取字段时，就会自动调用 MapAndJson 来完成转换。
// AttributeConverter<当前实体当前字段类型，字段在数据库中的类型String>>
@Component
public class MapAndJson implements AttributeConverter<Map<String,Object>,String> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {
        try {
            return mapper.writeValueAsString(stringObjectMap);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(9999);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        // 判空 - 数据库如果没有数据不判空会报错
        if (s == null || s.trim().isEmpty()) {
            return null;
        }
        try {
            return mapper.readValue(s, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(9999);
        }
    }
}


// 使用
@Convert(converter = MapAndJson.class )
private Map<String,Object> test;
```
此时前端返回数据：
```json
"test": {
    "key_id": 2,
    "value_id": 5,
    "value": "7英寸",
    "key": "尺寸"
}
```

2. 数据类型JSON与List的映射
- ListAndJson