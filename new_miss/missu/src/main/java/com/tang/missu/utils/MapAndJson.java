package com.tang.missu.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.missu.exception.http.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/* 处理单体Json的映射 */
//@Convert // 转换器,需要配AttributeConverter，JPA 在存取字段时，就会自动调用 MapAndJson 来完成转换。
// AttributeConverter<当前实体当前字段类型，字段在数据库中的类型String>>
@Component
public class MapAndJson implements AttributeConverter<Map<String,Object>,String> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {
        try {
            return mapper.writeValueAsString(stringObjectMap);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(9999);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        // 判空 - 数据库如果没有数据不判空会报错
        if (s == null || s.trim().isEmpty()) {
            return null;
        }
        try {
            return mapper.readValue(s, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(9999);
        }
    }
}
