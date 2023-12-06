package com.jason.tics.comment.domain;

import com.jason.tics.common.entity.DatedEntity;

/**
 * @author Jason
 * @since 2023/09/13 - 14:59
 */
public abstract class Comment extends DatedEntity {
    private String text;
    private final Long commentId;
    private final Long commentAreaId;
    private Long uid;

    public Comment(Long commentId, Long commentAreaId) {
        this.commentId = commentId;
        this.commentAreaId = commentAreaId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
