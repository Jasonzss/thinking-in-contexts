package com.jason.tics.comment.core;

import java.util.Date;

/**
 * @author Jason
 */
public class ReplyDo {
    private Long commentId;
    private Long replyId;
    private Long uid;
    private String content;

    private Date createTime;
    private Date updateTime;

    public static final String SORT_COLUMN_CREATE_TIME = "create_time";

    public ReplyDo() {
    }

    public ReplyDo(Long commentId, Long uid, String content) {
        this.commentId = commentId;
        this.uid = uid;
        this.content = content;
    }

    public ReplyDo(Reply reply) {
        setUid(reply.getUid());
        setCommentId(reply.getCommentId());
        setContent(reply.getContent());
        setReplyId(reply.getReplyId());
    }


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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
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
        return "ReplyDo{" +
                "commentId=" + commentId +
                ", replyId=" + replyId +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
