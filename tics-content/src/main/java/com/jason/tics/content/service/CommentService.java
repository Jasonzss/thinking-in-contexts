package com.jason.tics.content.service;


import cn.hutool.db.PageResult;
import com.jason.tics.comment.core.Comment;
import com.jason.tics.comment.meme.CommentMeme;
import com.jason.tics.comment.meme.Meme;
import com.jason.tics.content.domain.dto.CommentDto;

import java.util.List;

/**
 * @author Jason
 */
public interface CommentService {

    PageResult<Comment> listSortedComments(String aid, String sort, boolean descend, int page, int size);

    Comment addComment(CommentDto commentDto);

    void deleteComment(long cid);

    void deleteCommentAttachments(long cid, String[] attachment);

    String updateCommentContent(long cid, String content);

    void disLikeComment(long uid, long cid);

    String[] addCommentAttachments(long cid, String[] attachments);

    Comment getComment(long cid);

    List<Meme> listMemes();

    List<Meme> listMemes(int[] id);

    Meme saveMeme(String name, String image);

    void deleteMemes(int[] id);

    List<CommentMeme> listCommentMemes(long cid);

    void likeCommentMeme(long cid, long uid);

    void updateMeme(Meme meme);

    void unlikeCommentMeme(long cid, long uid);

    void likeComment(long uid, long cid);
}
