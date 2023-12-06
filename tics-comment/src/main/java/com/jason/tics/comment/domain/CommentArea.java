package com.jason.tics.comment.domain;

import cn.hutool.db.Page;
import cn.hutool.db.PageResult;

import java.util.List;

/**
 * @author Jason
 * @since 2023/09/13 - 15:20
 */
public class CommentArea<ID> {
    private final Long commentAreaId;

    private List<Long> hotCommentIds;

    private Integer commentsCount;

    private final ID subjectId;

    public CommentArea(Long commentAreaId, ID subjectId) {
        this.commentAreaId = commentAreaId;
        this.subjectId = subjectId;
    }

    public Long getCommentAreaId() {
        return commentAreaId;
    }

    public ID getSubjectId() {
        return subjectId;
    }

    public Comment saveComment(Comment comment) {
        return null;
    }

    public boolean deleteComment(long commentId) {
        return false;
    }

    public PageResult<Comment> getComments(Page page) {
        return null;
    }

    public List<Long> getHotCommentIds() {
        return hotCommentIds;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }
}
