package com.jason.tics.content.service.impl;

import cn.hutool.db.PageResult;
import com.jason.tics.comment.core.Comment;
import com.jason.tics.comment.meme.CommentMeme;
import com.jason.tics.comment.meme.Meme;
import com.jason.tics.comment.meme.MemeCommentArea;
import com.jason.tics.common.exception.CommonExceptionResponseEnum;
import com.jason.tics.common.exception.TicsException;
import com.jason.tics.content.domain.dto.CommentDto;
import com.jason.tics.content.service.CommentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason
 */
@Service
@Getter
@Setter
public class CommentServiceImpl implements CommentService {
    @Autowired
    private MemeCommentArea memeCommentArea;

    @Override
    public PageResult<Comment> listSortedComments(String id, String sort, boolean descend, int page, int size) {
        if (sort.equals(Comment.SORT_COLUMN_CREATE_TIME)) {
            if (!descend){
                return memeCommentArea.listCommentEarliestPage(id, page, size);
            } else {
                return memeCommentArea.listCommentLatestPage(id, page, size);
            }
        } else if (sort.equals(Comment.SORT_COLUMN_LIKE_NUM)){
            return memeCommentArea.listCommentMostLikedPage(id, page, size);
        } else {
            throw new TicsException(CommonExceptionResponseEnum.BAD_URL_PARAM);
        }
    }

    @Override
    public Comment addComment(CommentDto commentDto) {
        return memeCommentArea.addComment(commentDto.getSubjectId(), commentDto.getUid(), commentDto.getContent());
    }

    @Override
    public void deleteComment(long cid) {
        memeCommentArea.deleteComment(cid);
    }

    @Override
    public void deleteCommentAttachments(long cid, String[] attachment) {
        memeCommentArea.deleteCommentAttachments(cid, attachment);
    }

    @Override
    public String updateCommentContent(long cid, String content) {
        //TODO 这样写不太妥
        return memeCommentArea.modifyCommentContent(cid, content) ? content : "";
    }

    @Override
    public void likeComment(long uid, long cid) {
        memeCommentArea.likeComment(cid, uid);
    }

    @Override
    public void disLikeComment(long uid, long cid) {
        memeCommentArea.dislikeComment(cid, uid);
    }

    @Override
    public String[] addCommentAttachments(long cid, String[] attachments) {
        return memeCommentArea.addCommentAttachments(cid, attachments);
    }

    @Override
    public Comment getComment(long cid) {
        return memeCommentArea.getComment(cid);
    }

    @Override
    public List<Meme> listMemes() {
        return memeCommentArea.listMemes();
    }

    @Override
    public List<Meme> listMemes(int[] id) {
        return memeCommentArea.listMemes(id);
    }

    @Override
    public Meme saveMeme(String name, String image) {
        return memeCommentArea.addMemes(name, image);
    }

    @Override
    public void deleteMemes(int[] id) {
        memeCommentArea.deleteMemes(id);
    }

    @Override
    public List<CommentMeme> listCommentMemes(long cid) {
        return memeCommentArea.listCommentMemes(cid);
    }

    @Override
    public void likeCommentMeme(long cid, long uid) {
        memeCommentArea.likeCommentMeme(cid, uid);
    }

    @Override
    public void updateMeme(Meme meme) {
        memeCommentArea.updateMeme(meme);
    }

    @Override
    public void unlikeCommentMeme(long cid, long uid) {
        memeCommentArea.dislikeComment(cid, uid);
    }
}
