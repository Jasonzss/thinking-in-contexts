package com.jason.tics.comment.persistence.mybatis.mapper.meme;

import com.jason.tics.comment.meme.Meme;
import com.jason.tics.comment.persistence.mybatis.MybatisTest;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jason
 */
public class MemeMapperTest extends MybatisTest {
    private MemeMapper memeMapper;

    @Before
    public void init(){
        memeMapper = openSessionByXmlAndProp().getMapper(MemeMapper.class);
    }

    @Test
    public void testGetMeme(){
        log.debug("获取单个meme：" + memeMapper.getMeme(1));
    }

    @Test
    public void testListMemes(){
        log.debug("列出所有meme：" + memeMapper.listMemes());
    }

    @Test
    public void testAddMeme(){
        Meme meme = new Meme("huh", "www.huh.com");
        memeMapper.addMeme(meme);
        log.debug("新增meme的id：" + meme.getMemeId());
    }

    @Test
    public void testDeleteMeme(){
        log.debug("删除meme：" + memeMapper.deleteMeme(1));
    }

    @Test
    public void testUpdateMemeName(){
        memeMapper.updateMemeName(1, "come on");
        log.debug("修改meme图片地址：" + memeMapper.getMeme(1));
    }

    @Test
    public void testUpdateMemeImage(){
        memeMapper.updateMemeImage(1, "www.good.cn");
        log.debug("修改meme图片地址：" + memeMapper.getMeme(1));
    }


}
