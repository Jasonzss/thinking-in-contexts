package com.jason.tics.comment.persistence.mybatis;

import com.jason.tics.comment.core.CommentDo;
import com.jason.tics.comment.persistence.CommentDao;
import com.jason.tics.comment.persistence.mybatis.mapper.CommentMapper;

/**
 * @author Jason
 */
public class CommentDaoImpl implements CommentDao {
    private CommentMapper commentMapper;


    @Override
    public CommentDo getComment(long commentId) {
        return commentMapper.getComment(commentId);
    }

    @Override
    public CommentDo[] listComments(long[] commentIds) {
        return commentMapper.listComments(commentIds);
    }

    @Override
    public long[] listSubjectCommentIds(long subjectId) {
        return commentMapper.listSubjectCommentIds(subjectId);
    }

    @Override
    public CommentDo[] listSubjectCommentsByColumn(long subjectId, String columnName, boolean ascent, int offset, int limit) {
        return commentMapper.listSubjectCommentsByColumn(subjectId, columnName, ascent, offset, limit);
    }

    @Override
    public int countCommentBySubject(long subjectId) {
        return commentMapper.countCommentBySubject(subjectId);
    }

    @Override
    public int deleteComments(long[] commentIds) {
        return commentMapper.deleteComments(commentIds);
    }

    @Override
    public long addComment(long subjectId, long uid, String content) {
        CommentDo commentDo = new CommentDo();
        commentDo.setSubjectId(subjectId);
        commentDo.setUid(uid);
        commentDo.setContent(content);
        commentMapper.addComment(commentDo);
        return commentDo.getCommentId();
    }

    @Override
    public int updateCommentContent(long commentId, String content) {
        return commentMapper.updateCommentContent(commentId, content);
    }

    public CommentMapper getCommentMapper() {
        return commentMapper;
    }

    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
}
