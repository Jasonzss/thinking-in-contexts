package com.jason.tics.common.jpa.repository;

import com.jason.tics.common.jpa.entity.Dto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

/**
 * @author Jason
 */
public interface TicsRepository<T, ID> extends JpaRepository<T, ID> {
    T partialUpdate(ID id, Map<String, Object> attrs);

    T addByPojo(Dto<ID, T> dto);

    T updateByPojo(ID id, Dto<ID, T> dto);
}