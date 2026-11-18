package com.tang.missu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "grid_category")
public class GridCategory extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Size(max = 255)
    @Column(name = "title")
    private String title;

    @Size(max = 255)
    @Column(name = "img")
    private String img;

    @Size(max = 255)
    @Column(name = "name")
    private String name;


    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "root_category_id")
    private Integer rootCategoryId;


}