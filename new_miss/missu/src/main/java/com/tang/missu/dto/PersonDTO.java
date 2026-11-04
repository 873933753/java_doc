package com.tang.missu.dto;

/* DTO - 数据传输对象，处理接口参数的 */

import lombok.*;

//@Getter //lombok的Getter
//@Setter
@AllArgsConstructor //lombok - 生成全参的构造函数
//@NoArgsConstructor // lombok - 生成无参的构造函数
//@RequiredArgsConstructor
//@Builder // lombok
public class PersonDTO {
    @NonNull // lombok - 创建name的构造函数
    private String name;
    private Integer age;
}
