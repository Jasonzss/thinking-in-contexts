package com.jason.tics.comment.core;

import cn.hutool.db.PageResult;
import com.jason.tics.comment.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 列举业务上所有可能用上的方法
 *
 * @author Jason
 */
public class CommonCommentArea {
    private CommentDao commentDao;
    private CommentAttachmentDao commentAttachmentDao;
    private CommentLikeDao commentLikeDao;

    private ReplyDao replyDao;
    private ReplyAttachmentDao replyAttachmentDao;
    private ReplyLikeDao replyLikeDao;

    private int overviewReplyNum = 4;
    private int replyPageSize = 20;
    private int commentPageSize = 20;

    /* -----------------------------评论相关--------------------------------- */

    public Comment getComment(long commentId){
        CommentDo commentDo = commentDao.getComment(commentId);
        int approveNum = commentLikeDao.getCommentLikeNum(commentId);
        ReplyDo[] replies = replyDao.listCommentRepliesByColumn(commentId, ReplyDo.SORT_COLUMN_CREATE_TIME, true, 0, overviewReplyNum);

        Comment comment = new Comment(commentDo);
        comment.setAttachment(commentAttachmentDao.getCommentAttachments(commentId));
        comment.setReplies(replies);
        comment.setApproveNum(approveNum);

        return comment;
    }

    /**
     * 分页列出相关主题下的所有评论
     * @param subjectId 主题id
     * @return 所有评论的集合
     */
    public List<Comment> listComments(long subjectId){
        return null;
    }

    public Comment addComment(long subjectId, long uid, String content){
        long commentId = commentDao.addComment(subjectId, uid, content);

        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setUid(uid);
        comment.setContent(content);
        comment.setSubjectId(subjectId);

        return comment;
    }

    public void deleteComment(long commentId){
        long[] commentArr = new long[]{commentId};
        commentDao.deleteComments(commentArr);
        commentAttachmentDao.deleteCommentAttachments(commentArr);
        commentLikeDao.deleteCommentLikes(commentArr);

        long[] deletedReplies = replyDao.listCommentReplyIds(commentId);
        replyDao.deleteReplies(deletedReplies);
        replyAttachmentDao.deleteReplyAttachments(deletedReplies);
        replyLikeDao.deleteReplyLikes(deletedReplies);
    }

    public int modifyCommentContent(long commentId, String content){
        return commentDao.updateCommentContent(commentId, content);
    }

    public void approveComment(long commentId, long uid){
        commentLikeDao.addCommentLike(commentId, uid);
    }

    public void disapproveComment(long commentId, long uid){
        commentLikeDao.deleteCommentLikeById(commentId, uid);
    }

    public boolean isCommentApproved(long commentId, long uid){
        return commentLikeDao.getCommentLikeByUid(commentId, uid).isApproved();
    }

    /* -----------------------------回复相关--------------------------------- */

    public Reply getReply(long replyId){
        ReplyDo replyDo = replyDao.getReply(replyId);
        Reply reply = new Reply(replyDo);
        reply.setApproveNum(replyLikeDao.getReplyLikeNum(replyId));
        reply.setAttachment(replyAttachmentDao.getReplyAttachments(replyId));
        return reply;
    }

    public PageResult<Reply> listReplyEarliestPage(int commentId, int pageNum, int pageSize){
        ReplyDo[] commentDoArray = replyDao.listCommentRepliesByColumn(commentId, CommentDo.SORT_COLUMN_CREATE_TIME,
                true, pageNum*pageSize, pageSize);

        PageResult<Reply> res = new PageResult<>(pageNum, pageSize);
        res.addAll(listReplyDetails(commentDoArray));
        return res;
    }

    public PageResult<Reply> listReplyLatestPage(int commentId, int pageNum, int pageSize){
        ReplyDo[] commentDoArray = replyDao.listCommentRepliesByColumn(commentId, CommentDo.SORT_COLUMN_CREATE_TIME,
                false, pageNum*pageSize, pageSize);

        PageResult<Reply> res = new PageResult<>(pageNum, pageSize);
        res.addAll(listReplyDetails(commentDoArray));
        return res;
    }

    /**
     * 将回复表的所有数据查出来后再去其他表查出回复相关的其他数据
     * @param replyDos 所需要查其他信息的回复表数据
     * @return 包装过其他表数据的回复对象集合
     */
    private List<Reply> listReplyDetails(ReplyDo[] replyDos){
        List<Reply> replyList = new ArrayList<>();
        for(ReplyDo replyDo : replyDos){
            Reply reply = new Reply(replyDo);
            reply.setAttachment(replyAttachmentDao.getReplyAttachments(replyDo.getCommentId()));
            reply.setApproveNum(replyLikeDao.getReplyLikeNum(replyDo.getCommentId()));
            replyList.add(reply);
        }

        return replyList;
    }


    /* -----------------------------评论相关--------------------------------- */


    // getter setter

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentAttachmentDao getCommentAttachmentDao() {
        return commentAttachmentDao;
    }

    public void setCommentAttachmentDao(CommentAttachmentDao commentAttachmentDao) {
        this.commentAttachmentDao = commentAttachmentDao;
    }

    public CommentLikeDao getCommentApprovalDao() {
        return commentLikeDao;
    }

    public void setCommentApprovalDao(CommentLikeDao commentLikeDao) {
        this.commentLikeDao = commentLikeDao;
    }

    public ReplyDao getReplyDao() {
        return replyDao;
    }

    public void setReplyDao(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    public ReplyAttachmentDao getReplyAttachmentDao() {
        return replyAttachmentDao;
    }

    public void setReplyAttachmentDao(ReplyAttachmentDao replyAttachmentDao) {
        this.replyAttachmentDao = replyAttachmentDao;
    }

    public ReplyLikeDao getReplyApprovalDao() {
        return replyLikeDao;
    }

    public void setReplyApprovalDao(ReplyLikeDao replyLikeDao) {
        this.replyLikeDao = replyLikeDao;
    }

    public int getOverviewReplyNum() {
        return overviewReplyNum;
    }

    public void setOverviewReplyNum(int overviewReplyNum) {
        this.overviewReplyNum = overviewReplyNum;
    }

    public int getReplyPageSize() {
        return replyPageSize;
    }

    public void setReplyPageSize(int replyPageSize) {
        this.replyPageSize = replyPageSize;
    }

    public int getCommentPageSize() {
        return commentPageSize;
    }

    public void setCommentPageSize(int commentPageSize) {
        this.commentPageSize = commentPageSize;
    }
}
