package com.jason.tics.comment.core;

import java.util.Date;

/**
 * @author Jason
 */
public class Reply {
    private Long replyId;
    private Long commentId;
    private Long uid;
    private String content;

    private Date createTime;
    private Date updateTime;

    private Attachment[] attachment = null;
    private Integer approveNum = 0;

    public Reply() {
    }

    public Reply(ReplyDo replyDo) {
        setCommentId(replyDo.getCommentId());
        setUid(replyDo.getUid());
        setReplyId(replyDo.getReplyId());
        setContent(replyDo.getContent());
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

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
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

    public Attachment[] getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment[] attachment) {
        this.attachment = attachment;
    }

    public Integer getApproveNum() {
        return approveNum;
    }

    public void setApproveNum(Integer approveNum) {
        this.approveNum = approveNum;
    }
}
