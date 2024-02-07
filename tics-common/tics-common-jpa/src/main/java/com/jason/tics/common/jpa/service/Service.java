package com.jason.tics.common.jpa.service;

/**
 * 实现这个接口，而后从Spring中获取你的Service
 * 获取到的这个Service将自动实现大部分的CRUD，
 * 如果有些CRUD存在相关的特殊逻辑，可在类的字段上加上不同注解以优雅的完成CRUD
 * @author Jason
 */
public interface Service<T, ID> {

}
