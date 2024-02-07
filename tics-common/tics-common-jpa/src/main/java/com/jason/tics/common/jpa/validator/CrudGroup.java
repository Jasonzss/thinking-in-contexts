package com.jason.tics.common.jpa.validator;

/**
 * jpa已存在代替功能，详见 Column 的 insertable 和 updatable
 * @see javax.persistence.Column;
 * @author Jason
 */
@Deprecated
public class CrudGroup {
    public interface Insert {
    }

    public interface Update {
    }

    public interface Delete {
    }

    public interface Query {
    }
}
