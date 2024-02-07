package com.jason.tics.common.jpa.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * jpa已存在代替功能，详见 Column 的 insertable 和 updatable
 * @see javax.persistence.Column;
 * @author Jason
 */
@Target(FIELD)
@Retention(RUNTIME)
@Deprecated
public @interface Unchangeable {
}
