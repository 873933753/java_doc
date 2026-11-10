package com.tang.missu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "is_root", columnDefinition = "tinyint UNSIGNED not null")
    private Short isRoot;

    @Column(name = "parent_id", columnDefinition = "int UNSIGNED")
    private Long parentId;

    @Size(max = 255)
    @Column(name = "img")
    private String img;

    @Column(name = "`index`", columnDefinition = "int UNSIGNED")
    private Long index;

    @Column(name = "online", columnDefinition = "int UNSIGNED")
    private Long online;

    @Column(name = "level", columnDefinition = "int UNSIGNED")
    private Long level;


}