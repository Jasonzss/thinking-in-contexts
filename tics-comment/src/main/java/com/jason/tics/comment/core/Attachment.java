package com.jason.tics.comment.core;

import java.util.Date;

/**
 * @author Jason
 */
public class Attachment {
    private Long subjectId;
    private String attachmentUrl;
    private Date createTime;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "subjectId=" + subjectId +
                ", attachmentUrl='" + attachmentUrl + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
