package com.jason.tics.content.service.impl;

import com.jason.tics.content.ContentApplication;
import com.jason.tics.content.domain.VideoPost;
import com.jason.tics.content.domain.dto.VideoPostDto;
import com.jason.tics.content.service.VideoPostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jason
 */
@SpringBootTest(classes = ContentApplication.class)
@Slf4j
public class VideoPostServiceImplTest {
    @Autowired
    private VideoPostService videoPostService;

    @Test
    public void testAddVideo(){
        VideoPostDto videoPostDto = new VideoPostDto();
        videoPostDto.setVideoUrl("waf");
        videoPostDto.setCoverImageUrl("wfa");
        videoPostDto.setIntroduction("简介");
        videoPostDto.setTitle("fawfawfgg");
        VideoPost videoPost = videoPostService.addVideo(videoPostDto, 1L);
        log.info("新增视频：{}", videoPost);
    }
}
