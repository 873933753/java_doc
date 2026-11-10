package com.tang.missu.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SpuSimplifyVO {
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Size(max = 800)
    @Column(name = "subtitle", length = 800)
    private String subtitle;



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
}
