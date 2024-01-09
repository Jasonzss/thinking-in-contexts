package com.jason.tics.comment.persistence.mybatis.impl;

import com.jason.tics.comment.core.Attachment;
import com.jason.tics.comment.core.dao.CommentAttachmentDao;
import com.jason.tics.comment.persistence.mybatis.mapper.CommentAttachmentMapper;

/**
 * @author Jason
 */
public class CommentAttachmentDaoImpl implements CommentAttachmentDao {
    private CommentAttachmentMapper commentAttachmentMapper;

    public CommentAttachmentDaoImpl(CommentAttachmentMapper commentAttachmentMapper) {
        this.commentAttachmentMapper = commentAttachmentMapper;
    }

    @Override
    public Attachment[] getCommentAttachments(long commentId) {
        return commentAttachmentMapper.getCommentAttachments(commentId);
    }

    @Override
    public void saveCommentAttachments(long commentId, String[] attachmentUrls) {
        commentAttachmentMapper.saveCommentAttachments(commentId, attachmentUrls);
    }

    @Override
    public void deleteCommentAttachments(long[] commentIds) {
        commentAttachmentMapper.deleteCommentAttachments(commentIds);
    }

    @Override
    public void deleteCommentAttachments(long cid, String[] attachment) {
        commentAttachmentMapper.deleteCommentAttachmentsByUrlAndId(cid, attachment);
    }

    public CommentAttachmentMapper getAttachmentMapper() {
        return commentAttachmentMapper;
    }

    public void setAttachmentMapper(CommentAttachmentMapper commentAttachmentMapper) {
        this.commentAttachmentMapper = commentAttachmentMapper;
    }
}
