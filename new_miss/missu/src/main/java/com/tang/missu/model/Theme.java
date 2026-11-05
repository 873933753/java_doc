package com.tang.missu.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Theme {
    @Id
    private long id;
    private String name;
    private String title;

    /* 单向一对多 */
    @ManyToMany
    @JoinTable(name = "theme_spu",joinColumns = @JoinColumn(name = "theme_id"),inverseJoinColumns = @JoinColumn(name = "spu_id")) // 对中间表做命名，并指定外键名称
    private List<Spu> spuList;
}
