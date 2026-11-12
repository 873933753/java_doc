package com.tang.missu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.missu.exception.http.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.List;

@Component
public class ListAndJson implements AttributeConverter<List<Object>,String> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(List<Object> objects) {
        try {
            return mapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(9999);
        }
    }

    @Override
    public List<Object> convertToEntityAttribute(String s) {
        // 判空 - 数据库如果没有数据不判空会报错
        if (s == null || s.trim().isEmpty()) {
            return null;
        }
        try {
            List<Object> t = mapper.readValue(s,List.class);
            return t;
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(9999);
        }
    }
}
