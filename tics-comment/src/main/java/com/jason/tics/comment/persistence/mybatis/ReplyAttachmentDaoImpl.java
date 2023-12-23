package com.jason.tics.comment.persistence.mybatis;

import com.jason.tics.comment.core.Attachment;
import com.jason.tics.comment.persistence.ReplyAttachmentDao;
import com.jason.tics.comment.persistence.mybatis.mapper.ReplyAttachmentMapper;

/**
 * @author Jason
 */
public class ReplyAttachmentDaoImpl implements ReplyAttachmentDao {
    private ReplyAttachmentMapper replyAttachmentMapper;

    @Override
    public Attachment[] getReplyAttachments(long replyId) {
        return replyAttachmentMapper.getReplyAttachments(replyId);
    }

    @Override
    public int saveReplyAttachments(long replyId, String[] attachmentUrls) {
        return replyAttachmentMapper.saveReplyAttachments(replyId, attachmentUrls);
    }

    @Override
    public int deleteReplyAttachments(long[] replyIds) {
        return replyAttachmentMapper.deleteReplyAttachments(replyIds);
    }
}
