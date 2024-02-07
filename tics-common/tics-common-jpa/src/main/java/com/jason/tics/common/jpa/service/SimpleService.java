package com.jason.tics.common.jpa.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
@Getter
@Setter
public class SimpleService<R extends JpaRepository<T, ID>, ID, T> implements Service<T, ID>{
    @Autowired
    private R repository;
}
