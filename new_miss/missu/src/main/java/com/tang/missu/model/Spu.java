package com.tang.missu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.util.List;

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
