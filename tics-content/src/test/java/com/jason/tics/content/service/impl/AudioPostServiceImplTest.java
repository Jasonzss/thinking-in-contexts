package com.jason.tics.content.service.impl;

import com.jason.tics.content.ContentApplication;
import com.jason.tics.content.domain.AudioPost;
import com.jason.tics.content.domain.dto.AudioPostDto;
import com.jason.tics.content.service.AudioPostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jason
 */
@SpringBootTest(classes = ContentApplication.class)
@Slf4j
public class AudioPostServiceImplTest {
    @Autowired
    private AudioPostService audioPostService;

    @Test
    public void testAddVideo(){
        AudioPostDto audioPostDto = new AudioPostDto();
        audioPostDto.setAudioId("a1521");
        audioPostDto.setAudioUrl("fawf");
        audioPostDto.setIntroduction("fgawf");
        audioPostDto.setTitle("wfao");
        audioPostDto.setCoverImageUrl("awfa");
        AudioPost audioPost = audioPostService.addAudioPost(audioPostDto, 1L);
        log.info("新增音频：{}", audioPost);
    }
}
