package com.jason.tics.comment.core;

import java.util.Date;

/**
 * @author Jason
 */
public class CommentDo {
    private Long subjectId;
    private Long commentId;
    private Long uid;
    private String content;

    private Date createTime;
    private Date updateTime;

    public static final String SORT_COLUMN_CREATE_TIME = "create_time";

    public CommentDo() {
    }

    public CommentDo(Comment comment) {
        setUid(comment.getUid());
        setSubjectId(comment.getSubjectId());
        setContent(comment.getContent());
        setCommentId(comment.getCommentId());
    }

    //GETTER SETTER

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentDo{" +
                "subjectId=" + subjectId +
                ", commentId=" + commentId +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
