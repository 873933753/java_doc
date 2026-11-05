package com.tang.missu.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Banner {
    @Id // 给Entity打标识，javax.persistence的
    private long id;
    private String name;
    private String decscription;
    private String img;
    private String title;
}
