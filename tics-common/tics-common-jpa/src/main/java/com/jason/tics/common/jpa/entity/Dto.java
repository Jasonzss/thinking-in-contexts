package com.jason.tics.common.jpa.entity;

/**
 * 注意dto中不能存在id
 * @author Jason
 */
public interface Dto<ID, T> {
    T getSource();
}
