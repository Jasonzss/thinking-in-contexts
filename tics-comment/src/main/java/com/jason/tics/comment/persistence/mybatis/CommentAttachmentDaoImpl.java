package com.jason.tics.comment.persistence.mybatis;

import com.jason.tics.comment.core.Attachment;
import com.jason.tics.comment.persistence.CommentAttachmentDao;
import com.jason.tics.comment.persistence.mybatis.mapper.CommentAttachmentMapper;

/**
 * @author Jason
 */
public class CommentAttachmentDaoImpl implements CommentAttachmentDao {
    private CommentAttachmentMapper commentAttachmentMapper;

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

    public CommentAttachmentMapper getAttachmentMapper() {
        return commentAttachmentMapper;
    }

    public void setAttachmentMapper(CommentAttachmentMapper commentAttachmentMapper) {
        this.commentAttachmentMapper = commentAttachmentMapper;
    }
}
