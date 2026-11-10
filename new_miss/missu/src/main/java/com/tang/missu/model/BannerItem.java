package com.tang.missu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "banner_item")
public class BannerItem extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Size(max = 255)
    @Column(name = "img")
    private String img;

    @Size(max = 50)
    @Column(name = "keyword", length = 50)
    private String keyword;

    @Column(name = "type", columnDefinition = "smallint UNSIGNED not null")
    private Integer type;

    @Column(name = "banner_id", columnDefinition = "int UNSIGNED not null")
    private Long bannerId;

    @Size(max = 255)
    @Column(name = "name")
    private String name;
}