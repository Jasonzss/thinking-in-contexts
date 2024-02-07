package com.jason.tics.common.jpa.converter;

import com.google.gson.Gson;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import org.xerial.snappy.Snappy;

import javax.persistence.AttributeConverter;
import java.io.IOException;

/**
 * 注意数据库要用blob来放byte[]
 * 不过鉴于数据库的blob性能并不佳，所以大部分时候不推荐使用这个压缩转换器
 *
 * @author Jason
 */
public class CompressConverter implements AttributeConverter<Object, byte[]> {
    private final Gson gson = new Gson();


    @Override
    public byte[] convertToDatabaseColumn(Object attribute) {
        try {
            return Snappy.compress(gson.toJson(attribute));
        } catch (IOException e) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
    }

    @Override
    public Object convertToEntityAttribute(byte[] dbData) {
        try {
            return gson.fromJson(Snappy.uncompressString(dbData), Object.class);
        } catch (IOException e) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
    }
}
