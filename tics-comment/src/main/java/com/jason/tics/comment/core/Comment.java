package com.jason.tics.comment.core;

import java.util.Date;

/**
 * 单条评论的抽象基类，可根据需要的评论内容类型选择对应的子类
 *
 * @author Jason
 */
public class Comment {
    private Long subjectId;
    private Long commentId;
    private Long uid;
    private String content;

    private Date createTime;
    private Date updateTime;

    private ReplyDo[] replies;
    private Attachment[] attachment = null;
    private Integer approveNum = 0;

    public Comment() {
    }

    public Comment(CommentDo commentDo) {
        setCommentId(commentDo.getCommentId());
        setUid(commentDo.getUid());
        setSubjectId(commentDo.getSubjectId());
        setContent(commentDo.getContent());
    }

    //GETTER SETTER

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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


    public ReplyDo[] getReplies() {
        return replies;
    }

    public void setReplies(ReplyDo[] replies) {
        this.replies = replies;
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
