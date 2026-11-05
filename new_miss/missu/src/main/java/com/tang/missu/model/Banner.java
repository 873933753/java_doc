package com.tang.missu.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "banner2") // 数据库中表明重命名 banner1
public class Banner {
    @Id // 给Entity打标识，javax.persistence的
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id自增长
    private long id;

    @Column(length = 16) // 限制表中name长度16
    private String name;

    @Transient // 表明字段不会映射到表中
    private String description;
    private String img;
    private String title;

    @OneToMany
    @JoinColumn(name = "bannerId")
    List<BannerItem> bannerItems;
}
