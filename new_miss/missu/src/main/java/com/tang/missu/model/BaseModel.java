package com.tang.missu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

// 基类不能打上@Entity注解（一个实体一张表）
/* 问题：
* 1）未显示字段 - 未加Getter
* 2）字段显示null -基类不能打上@Entity注解（一个实体一张表），数据库字段未映射，打上注解@MappedSuperClass-表明它是Entity的一个父类
* 3）时间格式按照时间戳形式返回 - 配置jackson，Date
* 4）字段不返回给前端 @JsonIgnore (不够灵活，如果根据条件判断，需要将属性拷贝到新的属性下返回)
*  */
@Getter //
@Setter// 没有getter读不出来
@MappedSuperclass
public abstract class BaseModel {
    @Column(name = "create_time")
    @JsonIgnore
    private Date createTime;

    @Column(name = "update_time")
    @JsonIgnore
    private Date updateTime;

    @Column(name = "delete_time")
    @JsonIgnore
    private Date deleteTime;
}
