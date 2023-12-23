package com.jason.tics.comment.core;

import java.util.Date;

/**
 * @author Jason
 */
public class Like {
    private Long uid;
    private Long subjectId;
    private Date createTime;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isApproved(){
        return uid != null && subjectId != null;
    }

    @Override
    public String toString() {
        return "The User["+uid+"] likes the Comment["+subjectId+"] on "+ createTime;
    }
}
