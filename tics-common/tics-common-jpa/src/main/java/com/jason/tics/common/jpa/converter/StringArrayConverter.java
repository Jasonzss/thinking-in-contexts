package com.jason.tics.common.jpa.converter;

import com.google.gson.Gson;

import javax.persistence.AttributeConverter;

/**
 * @author Jason
 */
public class StringArrayConverter implements AttributeConverter<String[], String> {
    private final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, String[].class);
    }
}
