package com.jason.tics.comment.meme;

import com.jason.tics.comment.core.CommonCommentArea;
import com.jason.tics.comment.meme.dao.CommentMemeDao;
import com.jason.tics.comment.meme.dao.CommentMemeLikeDao;
import com.jason.tics.comment.meme.dao.MemeDao;
import com.jason.tics.comment.persistence.JCommentDaoFactory;
import com.jason.tics.comment.persistence.MemeDaoFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 */
@Getter
@Setter
public class MemeCommentArea extends CommonCommentArea {
    private CommentMemeDao commentMemeDao;
    private CommentMemeLikeDao commentMemeLikeDao;
    private MemeDao memeDao;

    public MemeCommentArea(MemeDaoFactory memeDaoFactory){
        super(memeDaoFactory);
        this.commentMemeDao = memeDaoFactory.getCommentMemeDao();
        this.commentMemeLikeDao = memeDaoFactory.getCommentMemeLikeDao();
        this.memeDao = memeDaoFactory.getMemeDao();
    }

    // 评论区公开的meme功能

    /**
     * 查询单个评论meme及其相关信息
     * @param commentMemeId 评论meme的id
     * @return 包括了点赞信息和评论meme本身所有信息的 CommentMeme
     */
    public CommentMeme getCommentMeme(long commentMemeId){
        CommentMemeDo commentMemeDo = commentMemeDao.getCommentMeme(commentMemeId);
        return new CommentMeme(commentMemeDo, memeDao.getMeme(commentMemeDo.getMemeId())
                , commentMemeLikeDao.countCommentMemeLikes(commentMemeId));
    }

    /**
     * 获取指定评论下的所有meme
     * @param commentId 指定评论的id
     * @return 该评论下的meme集合
     */
    public List<CommentMeme> listCommentMemes(long commentId){
        CommentMemeDo[] commentMemeDos = commentMemeDao.listCommentMemes(commentId);
        List<CommentMeme> commentMemes = new ArrayList<>();

        for(CommentMemeDo commentMemeDo : commentMemeDos){
            commentMemes.add(new CommentMeme(commentMemeDo, memeDao.getMeme(commentMemeDo.getMemeId())
                    , commentMemeLikeDao.countCommentMemeLikes(commentMemeDo.getCommentMemeId())));
        }

        return commentMemes;
    }

    /**
     * 点赞指定评论下的指定meme
     * @param uid 点赞的用户id
     * @param commentMemeId 被点赞的评论meme
     */
    public void likeCommentMeme(long uid, long commentMemeId){
        if (commentMemeLikeDao.addCommentMemeLike(commentMemeId, uid) > 0) {
            commentMemeDao.updateCommentMemeLikeNum(commentMemeId, 1);
        }
    }

    /**
     * 点赞指定评论下的指定meme，若没有则新增后再点赞
     * @param uid 点赞用户
     * @param memeId 新增的meme的id
     * @param commentId 被添加meme的评论的id
     */
    public void likeCommentMeme(long uid, int memeId, long commentId){
        CommentMemeDo[] commentMemeDos = commentMemeDao.listCommentMemes(commentId);

        for(CommentMemeDo commentMemeDo : commentMemeDos){
            if(commentMemeDo.getMemeId() == memeId){
                //meme已存在无需新增，直接点赞
                likeCommentMeme(uid, commentMemeDo.getCommentMemeId());
                return;
            }
        }

        //meme不存在，先新增后点赞
        CommentMemeDo commentMemeDo = new CommentMemeDo(commentId, memeId);
        commentMemeDao.addCommentMeme(commentMemeDo);
        likeCommentMeme(uid, commentMemeDo.getCommentMemeId());
    }

    /**
     * 取消点赞指定评论下的指定meme
     * @param uid 取消点赞的用户id
     * @param commentMemeId 被取消点赞的评论meme
     */
    public void dislikeCommentMeme(long uid, long commentMemeId){
        if (commentMemeLikeDao.deleteCommentMemeLikeByUid(commentMemeId, uid) > 0) {
            commentMemeDao.updateCommentMemeLikeNum(commentMemeId, -1);
        }
    }

    /**
     * 判断用户是否已经点赞指定评论下的meme
     * @param uid 用户id
     * @param commentMemeId 评论meme
     */
    public boolean isCommentMemeLiked(long uid, long commentMemeId){
        return commentMemeLikeDao.getCommentMemeLike(commentMemeId, uid) != null;
    }

    /**
     * 删除指定评论下的所有meme
     * @param commentId 评论id
     */
    public void deleteCommentMemes(long commentId){
        CommentMemeDo[] commentMemeDos = commentMemeDao.listCommentMemes(commentId);
        commentMemeDao.deleteCommentMemes(commentId);
        for(CommentMemeDo commentMemeDo : commentMemeDos){
            commentMemeLikeDao.deleteCommentMemeLikes(commentMemeDo.getCommentMemeId());
        }
    }

    // 后台meme的管理功能

    public Meme getMeme(int memeId){
        return memeDao.getMeme(memeId);
    }

    public List<Meme> listMemes(){
        return memeDao.listMemes();
    }

    public List<Meme> listMemes(int[] ids){
        return memeDao.listMemes(ids);
    }

    public Meme addMemes(String memeName, String memeImageUrl){
        Meme meme = new Meme(memeName, memeImageUrl);
        memeDao.addMeme(meme);
        return meme;
    }

    public void deleteMeme(int memeId){
        memeDao.deleteMeme(memeId);
    }

    public void deleteMemes(int[] memeIds){
        for(int id : memeIds){
            deleteMeme(id);
        }
    }

    public void updateMeme(Meme meme){
        memeDao.updateMeme(meme);
    }
}
