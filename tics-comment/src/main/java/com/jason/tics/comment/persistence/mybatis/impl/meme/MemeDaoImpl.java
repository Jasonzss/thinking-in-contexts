package com.jason.tics.comment.persistence.mybatis.impl.meme;

import com.jason.tics.comment.meme.Meme;
import com.jason.tics.comment.meme.dao.MemeDao;
import com.jason.tics.comment.persistence.mybatis.mapper.meme.MemeMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Jason
 */
@Getter
@Setter
public class MemeDaoImpl implements MemeDao {
    private MemeMapper memeMapper;

    public MemeDaoImpl(MemeMapper memeMapper) {
        this.memeMapper = memeMapper;
    }

    @Override
    public Meme getMeme(int memeId) {
        return memeMapper.getMeme(memeId);
    }

    @Override
    public List<Meme> listMemes() {
        return memeMapper.listMemes();
    }

    @Override
    public List<Meme> listMemes(int[] memeIds) {
        return memeMapper.listMemesByIds(memeIds);
    }


    @Override
    public int addMeme(Meme meme) {
        return memeMapper.addMeme(meme);
    }

    @Override
    public int deleteMeme(int memeId) {
        return memeMapper.deleteMeme(memeId);
    }

    @Override
    public int updateMeme(Meme meme) {
        int i = 0;
        if (meme.getMemeImage() != null){
            i = memeMapper.updateMemeImage(meme.getMemeId(), meme.getMemeImage());
        }
        if(meme.getMemeName() != null){
            i = memeMapper.updateMemeName(meme.getMemeId(), meme.getMemeName());
        }
        return i;
    }
}
