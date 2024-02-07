package com.jason.tics.common.jpa.converter;

import javax.persistence.AttributeConverter;

/**
 * @author Jason
 */
public class BooleanConverter implements AttributeConverter<Boolean, Byte> {

    public static final byte TRUE = Byte.parseByte("1");
    public static final byte NULL = Byte.parseByte("0");
    public static final byte FALSE = Byte.parseByte("-1");

    @Override
    public Byte convertToDatabaseColumn(Boolean attribute) {
        return attribute == null ? NULL : attribute ? TRUE : FALSE;
    }

    @Override
    public Boolean convertToEntityAttribute(Byte dbData) {
        return dbData == NULL ? null : dbData == TRUE;
    }
}
