package com.jason.tics.comment.meme.dao;

import com.jason.tics.comment.meme.Meme;

import java.util.List;

/**
 * @author Jason
 */
public interface MemeDao {
    Meme getMeme(int memeId);

    List<Meme> listMemes();

    List<Meme> listMemes(int[] memeIds);

    int addMeme(Meme meme);

    int deleteMeme(int memeId);

    int updateMeme(Meme meme);
}
