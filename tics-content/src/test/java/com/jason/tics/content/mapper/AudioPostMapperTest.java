package com.jason.tics.content.mapper;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jason.tics.content.ContentApplication;
import com.jason.tics.content.domain.AudioPost;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jason
 */
@SpringBootTest(classes = ContentApplication.class)
@Transactional
@Slf4j
public class AudioPostMapperTest {
    @Autowired
    private AudioPostMapper audioPostMapper;

    @Test
    public void testSelectById(){
        log.info("查找Audio__"+audioPostMapper.selectById(1));
    }

    @Test
    public void testUpdate(){
        AudioPost tobeUpdated = new AudioPost();
        AudioPost condition = new AudioPost();
        tobeUpdated.setAudioUrl("修改后的url");
        condition.setAudioId("a1");
        log.info("查找Audio："+audioPostMapper.selectById("a1"));
        log.info("修改Audio："+audioPostMapper.update(tobeUpdated, new UpdateWrapper<>(condition)));
        log.info("查找Audio："+audioPostMapper.selectById("a1"));
    }

    @Test
    public void testInsert(){
        AudioPost audioPost = new AudioPost();
        audioPost.setAuthorId(1L);
        audioPost.setTitle("标题");
        audioPost.setIntroduction("简介");
        audioPost.setAudioUrl("url");
        audioPost.setCreateTime(DateUtil.date());
        audioPostMapper.insert(audioPost);
        log.info("查找Audio："+audioPost);
    }
}
