package com.tang.missu.model;

import com.tang.missu.utils.ListAndJson;
import com.tang.missu.utils.MapAndJson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "sku")
public class Sku extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "discount_price", precision = 10, scale = 2)
    private BigDecimal discountPrice;

    @Column(name = "online", columnDefinition = "tinyint UNSIGNED not null")
    private Boolean online;

    @Size(max = 255)
    @Column(name = "img")
    private String img;

    @Size(max = 255)
    @Column(name = "title")
    private String title;

    @Column(name = "spu_id", columnDefinition = "int UNSIGNED not null")
    private Long spuId; // 外键

    @Lob
    @Column(name = "specs")
//    private String specs; // 规格相关，String前端不好处理
    // 这里定义Spec类，但是实体中的成员类型要和数据库一致，specs在数据库中是json类型，而且其他地方也要每次定义类
    // 想要实现一个共同的，且数组类型的映射和单一的Object类型的映射不一样
    @Convert(converter = ListAndJson.class)
    private List<Object> specs;

    @Size(max = 255)
    @Column(name = "code")
    private String code; // 每一个sku的唯一标识，前端会使用到

    @Column(name = "stock", columnDefinition = "int UNSIGNED not null")
    private Long stock; // 库存量

    @Column(name = "category_id", columnDefinition = "int UNSIGNED")
    private Long categoryId;

    @Column(name = "root_category_id", columnDefinition = "int UNSIGNED")
    private Long rootCategoryId;

//    private String test; // 测试单体Json对象映射
    @Convert(converter = MapAndJson.class )
    private Map<String,Object> test;

}