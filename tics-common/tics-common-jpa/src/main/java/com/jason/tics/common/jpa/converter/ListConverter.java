package com.jason.tics.common.jpa.converter;

import com.google.gson.Gson;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * 注意，正常情况下一对多的数据是需要另取一个表储存数据的。但如果该数据只是用于查看，且长度都比较短，
 * 且不会用作查询数据时的筛选条件时，可为了管理方便使用此转换器将其放置在同一张表里
 *
 * @author Jason
 */
public class ListConverter implements AttributeConverter<List<?>, String> {
    private final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(List attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public List<?> convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, List.class);
    }
}
