package com.jason.tics.common.entity;

import java.util.Date;

/**
 * @author Jason
 */
public class DatedEntity {
    private Date createDate;
    private Date updateDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
