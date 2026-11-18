package com.tang.missu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "theme")
public class Theme  extends  BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Size(max = 60)
    @Column(name = "title", length = 60)
    private String title;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 30)
    @Column(name = "name", length = 30)
    private String name;


    @Size(max = 30)
    @Column(name = "tpl_name", length = 30)
    private String tplName; // 前端用的模板名


    @Size(max = 255)
    @Column(name = "entrance_img")
    private String entranceImg;

    @Size(max = 255)
    @Column(name = "extend")
    private String extend;

    @Size(max = 255)
    @Column(name = "internal_top_img")
    private String internalTopImg;

    @Size(max = 255)
    @Column(name = "title_img")
    private String titleImg;

    @Column(name = "online", columnDefinition = "tinyint UNSIGNED")
    private Short online;

    // theme 和 spu 是 多对多的关系，一个主题可以有多个spu，一个spu也可以属于多个主题
    @ManyToMany(fetch = FetchType.LAZY) // 多对多默认就是 LAZY
    /* @JoinTable - 配置中间表
    * - name = "theme_spu" - 中间表名
    * - joinColumns = @JoinColumn(name = "theme_id") - 当前实体在中间表的外键
    * - inverseJoinColumns = @JoinColumn(name = "spu_id") - 对方实体在中间表的外键
    * */
    @JoinTable(name = "theme_spu",joinColumns = @JoinColumn(name = "theme_id"),inverseJoinColumns = @JoinColumn(name = "spu_id")) // 中间表
    private List<Spu> spuList;

}