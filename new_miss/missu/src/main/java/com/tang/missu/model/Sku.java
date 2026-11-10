package com.tang.missu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

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
    private Long spuId;

    @Lob
    @Column(name = "specs")
    private String specs;

    @Size(max = 255)
    @Column(name = "code")
    private String code;

    @Column(name = "stock", columnDefinition = "int UNSIGNED not null")
    private Long stock;

    @Column(name = "category_id", columnDefinition = "int UNSIGNED")
    private Long categoryId;

    @Column(name = "root_category_id", columnDefinition = "int UNSIGNED")
    private Long rootCategoryId;


}