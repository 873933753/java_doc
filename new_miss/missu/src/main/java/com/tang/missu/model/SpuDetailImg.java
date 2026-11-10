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
@Table(name = "spu_detail_img")
public class SpuDetailImg extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "spu_id", columnDefinition = "int UNSIGNED")
    private Long spuId;

    @Column(name = "`index`", columnDefinition = "int UNSIGNED not null")
    private Long index;

}