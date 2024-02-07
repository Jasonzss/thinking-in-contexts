package com.jason.tics.common.security.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 打在属性上，表示这个字段在数据库查询时可以用于排序
 * @author Jason
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Sortable {

}
