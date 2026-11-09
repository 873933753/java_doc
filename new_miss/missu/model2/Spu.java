package com.tang.missu.model2;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Spu {
    @Id
    private long id;
    private String name;
    private String title;

//    private long themeId;

//    @ManyToMany
//    private List<Theme> themeList;
}
