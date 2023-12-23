package com.jason.tics.comment.persistence;

/**
 * 使用抽象工厂模式，加工出不同评论类型不同持久化接口的Dao对象
 *
 * @author Jason
 */
public interface JCommentDaoFactory {
    CommentDao getCommentDao();

    CommentLikeDao getSimpleApproveDao();
}
