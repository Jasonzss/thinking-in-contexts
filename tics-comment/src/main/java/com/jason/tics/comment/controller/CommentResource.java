package com.jason.tics.comment.controller;

import cn.hutool.db.Page;
import cn.hutool.db.PageResult;
import com.jason.tics.comment.domain.Comment;

/**
 * @author Jason
 */
public class CommentResource {
    public Comment saveComment(Comment comment) {
        return null;
    }

    public boolean deleteComment(long commentId) {
        return false;
    }

    public PageResult<Comment> getComments(Page page) {
        return null;
    }
}
