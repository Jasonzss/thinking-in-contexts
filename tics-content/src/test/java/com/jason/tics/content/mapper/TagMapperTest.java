package com.jason.tics.content.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jason.tics.content.ContentApplication;
import com.jason.tics.content.domain.Tag;
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
public class TagMapperTest {
    @Autowired
    private TagMapper tagMapper;

    @Test
    public void testGet(){
        Wrapper<Tag> wrapper = new QueryWrapper<>();
        log.info(""+tagMapper.selectList(wrapper));
    }
}
