package com.tang.missu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class SchoolDTO {
    @Length(min = 1,max = 2,message = "学校名字不超过2位哦")
    private String schoolName;

    @Range(min=1,max=3,message = "年级最大不超过3")
    private int classLevel;

}
