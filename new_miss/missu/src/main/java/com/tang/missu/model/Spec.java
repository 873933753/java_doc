package com.tang.missu.model;

import lombok.Getter;
import lombok.Setter;

// 用于返回前端的specs的格式处理
@Getter
@Setter
public class Spec {
    private String key;
    private Long keyId;
    private String value;
    private  Long valueId;
}
