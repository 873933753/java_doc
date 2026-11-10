package com.tang.missu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "spu")
public class Spu extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Size(max = 800)
    @Column(name = "subtitle", length = 800)
    private String subtitle;

    @Column(name = "category_id", columnDefinition = "int UNSIGNED not null")
    private Long categoryId;

    @Column(name = "root_category_id")
    private Long rootCategoryId;

    @Column(name = "online", columnDefinition = "tinyint UNSIGNED not null")
    private Boolean online;


    @Size(max = 20)
    @NotNull
    @Column(name = "price", nullable = false, length = 20)
    private String price;

    @Column(name = "sketch_spec_id", columnDefinition = "int UNSIGNED")
    private Long sketchSpecId;

    @Column(name = "default_sku_id")
    private Long defaultSkuId;

    @Size(max = 255)
    @Column(name = "img")
    private String img;

    @Size(max = 20)
    @Column(name = "discount_price", length = 20)
    private String discountPrice;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 30)
    @Column(name = "tags", length = 30)
    private String tags;

    @Column(name = "is_test", columnDefinition = "tinyint UNSIGNED")
    private Boolean isTest;

//    @Lob
//    @Column(name = "spu_theme_img")
//    private String spuThemeImg;

    @Size(max = 255)
    @Column(name = "for_theme_img")
    private String forThemeImg;

    /* 导航关系配置
    * Spu 对 SpuImg - 1对多，一个商品有多张Banner图。
    * SPu 对 SpuDetailImg - 1对多，一个商品对应多张详情图。
    * Spu 对 Sku - 1对多，一个商品对应多个规格SKU。
    *  */

    @OneToMany
    @JoinColumn(name = "spu_id")
    private List<SpuImg> spuImgs;

    @OneToMany
    @JoinColumn(name = "spu_id")
    private  List<SpuDetailImg> spuDetailImgs;

    @OneToMany
    @JoinColumn(name = "spu_id")
    private List<Sku> skus;

}