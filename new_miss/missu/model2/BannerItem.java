package com.tang.missu.model2;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BannerItem {
    @Id
    private long id;
    private String img;
    private String keyword;
    private Short type;
    private String name;

    // 外键 - 与banner建立关系
    private long bannerId;
}
