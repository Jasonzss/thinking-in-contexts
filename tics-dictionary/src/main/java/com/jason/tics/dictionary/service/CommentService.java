package com.jason.tics.dictionary.service;

import cn.hutool.db.PageResult;
import com.jason.tics.comment.core.Comment;
import com.jason.tics.dictionary.domain.dto.CommentDto;

/**
 * @author Jason
 */
public interface CommentService {
    PageResult<Comment> listSortedComments(String word, String sort, boolean descend, int page, int size);

    Comment getComment(long cid);

    Comment addComment(CommentDto commentDto);

    void deleteComment(long cid);

    String updateCommentContent(long cid, String content);

    void deleteCommentAttachments(long cid, String[] attachment);

    String[] addCommentAttachments(long cid, String[] attachments);

    void disLikeComment(long uid, long cid);

    void likeComment(long uid, long cid);
}
