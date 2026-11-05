package com.tang.missu.dto;

/* DTO - 数据传输对象，处理接口参数的 */

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;

@Getter //lombok的Getter
//@Setter
@AllArgsConstructor //lombok - 生成全参的构造函数
@NoArgsConstructor // lombok - 生成无参的构造函数
//@RequiredArgsConstructor
//@Builder // lombok
public class PersonDTO {
    @NonNull // lombok - 创建name的构造函数
    @Length(min = 1,max = 3,message = "长度在1-3之间")
    private String name;
    @Max(value = 10,message = "最大不能超过10哦")
    private Integer age;

    @Valid
    private SchoolDTO schoolDTO;
}
