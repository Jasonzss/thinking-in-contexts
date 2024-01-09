package com.jason.tics.comment.core;

import cn.hutool.db.PageResult;
import com.jason.tics.comment.core.dao.*;
import com.jason.tics.comment.core.table.CommentDo;
import com.jason.tics.comment.core.table.ReplyDo;
import com.jason.tics.comment.persistence.JCommentDaoFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Map.Entry;
import java.util.*;

/**
 * 列举业务上所有可能用上的方法
 *
 * @author Jason
 */
@Getter
@Setter
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

    public CommonCommentArea(JCommentDaoFactory jCommentDaoFactory){
        setCommentDao(jCommentDaoFactory.getCommentDao());
        setCommentAttachmentDao(jCommentDaoFactory.getCommentAttachmentDao());
        setCommentLikeDao(jCommentDaoFactory.getCommentLikeDao());
        setReplyDao(jCommentDaoFactory.getReplyDao());
        setReplyAttachmentDao(jCommentDaoFactory.getReplyAttachmentDao());
        setReplyLikeDao(jCommentDaoFactory.getReplyLikeDao());
    }

    /* -----------------------------评论相关--------------------------------- */

    /**
     * 查询单条评论及其相关信息
     * @param commentId 评论id
     * @return 指定的评论
     */
    public Comment getComment(long commentId){
        CommentDo commentDo = commentDao.getComment(commentId);
        Comment comment = new Comment(commentDo);

        comment.setAttachment(commentAttachmentDao.getCommentAttachments(commentId));
        comment.setLikeNum(commentLikeDao.getCommentLikeNum(commentId));
        comment.setReplies(replyDao.listCommentRepliesByColumn(commentId,
                ReplyDo.SORT_COLUMN_CREATE_TIME, true, 0, overviewReplyNum));

        return comment;
    }

    private List<Comment> listComments(long[] commentIds){
        List<Comment> comments = new ArrayList<>();
        for(long commentId : commentIds){
            comments.add(getComment(commentId));
        }
        return comments;
    }

    /**
     * 分页列出相关主题下的所有评论，性能不佳，建议在评论数不多的情况下使用
     * @param subjectId 主题id
     * @return 所有评论的集合
     */
    public List<Comment> listComments(String subjectId){
        long[] commentIds = commentDao.listSubjectCommentIds(subjectId);
        return listComments(commentIds);
    }

    /**
     *  按发布时间顺序分页查询评论
     * @param subjectId 主题id
     * @param pageNum 页数
     * @param pageSize 每页数据量
     * @return 一页评论
     */
    public PageResult<Comment> listCommentEarliestPage(String subjectId, int pageNum, int pageSize){
        CommentDo[] commentDos = commentDao.listSubjectCommentsByColumn(subjectId
                , CommentDo.SORT_COLUMN_CREATE_TIME, true, pageNum*pageSize, pageSize);
        PageResult<Comment> commentPageResult = new PageResult<>(pageNum, pageSize);
        long[] ids = new long[commentDos.length];
        for(int i = 0; i < commentDos.length; i++){
            ids[i] = commentDos[i].getCommentId();
        }
        commentPageResult.addAll(listComments(ids));
        return commentPageResult;
    }

    /**
     *  按发布时间倒序分页查询评论
     * @param subjectId 主题id
     * @param pageNum 页数
     * @param pageSize 每页数据量
     * @return 一页评论
     */
    public PageResult<Comment> listCommentLatestPage(String subjectId, int pageNum, int pageSize){
        CommentDo[] commentDos = commentDao.listSubjectCommentsByColumn(subjectId
                , CommentDo.SORT_COLUMN_CREATE_TIME, false, pageNum*pageSize, pageSize);
        PageResult<Comment> commentPageResult = new PageResult<>(pageNum, pageSize);
        long[] idArr = new long[commentDos.length];
        for(int i = 0; i < commentDos.length; i++){
            idArr[i] = commentDos[i].getCommentId();
        }
        commentPageResult.addAll(listComments(idArr));
        return commentPageResult;
    }

    public PageResult<Comment> listCommentMostLikedPage(String subjectId, int pageNum, int pageSize){
        long[] commentIds = commentDao.listSubjectCommentIds(subjectId);
        Map<Long, Integer> commentLikedNum = new HashMap<>();
        for (long commentId : commentIds){
            commentLikedNum.put(commentId, commentLikeDao.getCommentLikeNum(commentId));
        }

        List<Entry<Long, Integer>> list = new ArrayList<>(commentLikedNum.entrySet());
        list.sort(Entry.comparingByValue());

        PageResult<Comment> comments = new PageResult<>();

        for(int i = pageNum*pageSize; i < list.size() && i < pageNum*pageSize+pageSize; i++){
            comments.add(getComment(list.get(i).getKey()));
        }

        return comments;
    }

    /**
     * 添加评论
     * @param subjectId 被评论的主题的id
     * @param uid 用户id
     * @param content 评论内容
     * @return 新增的评论
     */
    public Comment addComment(String subjectId, long uid, String content){
        long commentId = commentDao.addComment(subjectId, uid, content);
        return getComment(commentId);
    }

    /**
     * 删除评论及其相关其他信息
     * @param commentId 被删除的评论的id
     */
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

    /**
     * 修改评论内容
     * @param commentId 被修改评论的id
     * @param content 修改后的内容
     * @return 是否修改成功
     */
    public boolean modifyCommentContent(long commentId, String content){
        return commentDao.updateCommentContent(commentId, content) > 0;
    }

    /**
     * 点赞评论
     * @param commentId 评论id
     * @param uid 用户id
     */
    public void likeComment(long commentId, long uid){
        if(commentLikeDao.addCommentLike(commentId, uid) > 0){
            //TODO 点赞作为频繁操作不宜直接调用，可加个缓存层
            commentDao.updateCommentLikeNum(commentId, 1);
        }
    }

    /**
     * 取消点赞评论
     * @param commentId 评论id
     * @param uid 用户id
     */
    public void dislikeComment(long commentId, long uid){
        if (commentLikeDao.deleteCommentLikeById(commentId, uid) > 0) {
            commentDao.updateCommentLikeNum(commentId, -1);
        }
    }

    /**
     * 查看是否已经点赞评论
     * @param commentId 评论id
     * @param uid 用户id
     * @return 是否点赞
     */
    public boolean isCommentLiked(long commentId, long uid){
        return commentLikeDao.getCommentLikeByUid(commentId, uid) != null;
    }

    /**
     * 判断评论合集中哪些是点赞过的
     * @param uid 用户id
     * @param commentIds 评论id集合
     * @return 已被点赞的评论的id集合
     */
    public List<Long> isCommentsLiked(long uid, long... commentIds){
        List<Long> likedCommentIds = new ArrayList<>();

        for (long id : commentIds){
            if(isCommentLiked(id, uid)){
                likedCommentIds.add(id);
            }
        }

        return likedCommentIds;
    }

    /* -----------------------------回复相关--------------------------------- */

    /**
     * 查询单条回复及其相关信息
     * @param replyId 回复id
     * @return 回复内容
     */
    public Reply getReply(long replyId){
        Reply reply = new Reply(replyDao.getReply(replyId));

        reply.setLikeNum(replyLikeDao.getReplyLikeNum(replyId));
        reply.setAttachment(replyAttachmentDao.getReplyAttachments(replyId));

        return reply;
    }

    /**
     * 按回复发布时间顺序分页查询回复
     * @param commentId 评论id
     * @param pageNum 页数
     * @param pageSize 单页数据量
     * @return 一页回复
     */
    public PageResult<Reply> listReplyEarliestPage(int commentId, int pageNum, int pageSize){
        ReplyDo[] commentDoArray = replyDao.listCommentRepliesByColumn(commentId, CommentDo.SORT_COLUMN_CREATE_TIME,
                true, pageNum*pageSize, pageSize);

        PageResult<Reply> res = new PageResult<>(pageNum, pageSize);
        res.addAll(listReplyDetails(commentDoArray));
        return res;
    }

    /**
     * 按回复发布时间倒序分页查询回复
     * @param commentId 评论id
     * @param pageNum 页数
     * @param pageSize 单页数据量
     * @return 一页回复
     */
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
            reply.setLikeNum(replyLikeDao.getReplyLikeNum(replyDo.getCommentId()));
            replyList.add(reply);
        }

        return replyList;
    }

    /**
     * 点赞回复
     * @param replyId 回复id
     * @param uid 点赞用户的id
     */
    public void likeReply(long replyId, long uid){
        if (replyLikeDao.addReplyLike(replyId, uid) > 0) {
            replyDao.updateReplyLikeNum(replyId, 1);
        }
    }
    /**
     * 取消点赞回复
     * @param replyId 回复id
     * @param uid 取消点赞用户的id
     */
    public void dislikeReply(long replyId, long uid){
        if (replyLikeDao.addReplyLike(replyId, uid) > 0) {
            replyDao.updateReplyLikeNum(replyId, -1);
        }
    }

    /**
     * 回复是否被点赞
     * @param uid 检查点赞的用户
     * @param replyIds 待检查的回复id集合
     * @return 已被点赞的回复的id集合
     */
    public List<Long> isRepliesLiked(long uid, long... replyIds){
        List<Long> likedReplies = new ArrayList<>();

        for(long id : replyIds){
            if(replyLikeDao.getReplyLikeByUid(id, uid) != null){
                likedReplies.add(id);
            }
        }

        return likedReplies;
    }

    public void deleteCommentAttachments(long cid, String[] attachment) {
        commentAttachmentDao.deleteCommentAttachments(cid, attachment);
    }

    public String[] addCommentAttachments(long cid, String[] attachments) {
        commentAttachmentDao.saveCommentAttachments(cid, attachments);
        return attachments;
    }
}
