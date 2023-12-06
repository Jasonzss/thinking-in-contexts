package com.jason.tics.comment.domain;

import com.jason.tics.comment.domain.meme.Meme;

import java.util.List;

/**
 * @author Jason
 *
 * 其他用户和评论的交互不只是只有点赞，还有添加各种meme的标签
 */
public class MemeComment extends ApprovableComment{
    private List<Meme> memes;

    public MemeComment(Long commentId) {
        super(commentId);
    }

    public MemeComment(Long commentId, Integer approveNum) {
        super(commentId, approveNum);
    }

    public List<Meme> getMemes() {
        return memes;
    }

    public void setMemes(List<Meme> memes) {
        this.memes = memes;
    }
}
