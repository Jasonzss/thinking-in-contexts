package com.jason.tics.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jason.tics.api.content.bo.VideoPostBo;
import com.jason.tics.common.rocketmq.annotation.SendMessage;
import com.jason.tics.common.rocketmq.aop.PayloadSource;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.content.domain.VideoPost;
import com.jason.tics.content.domain.dto.VideoPostDto;
import com.jason.tics.content.mapper.VideoPostMapper;
import com.jason.tics.content.service.VideoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class VideoPostServiceImpl implements VideoPostService {
    @Autowired
    private VideoPostMapper videoPostMapper;

    @Override
    public VideoPost getVideo(String id) {
        return videoPostMapper.selectById(id);
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC, targetPojo = VideoPostBo.class)
    @Override
    public VideoPost addVideo(VideoPostDto videoPostDto, long uid) {
        VideoPost videoPost = BeanUtil.copyProperties(videoPostDto, VideoPost.class);
        videoPost.setAuthorId(uid);
        videoPostMapper.insert(videoPost);
        return videoPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC, targetPojo = VideoPostBo.class)
    @Override
    public VideoPost updateVideo(String id, String file) {
        VideoPost videoPost = new VideoPost();
        videoPost.setVideoId(id);
        videoPost.setVideoUrl(file);
        videoPostMapper.updateById(videoPost);
        return videoPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC, targetPojo = VideoPostBo.class)
    @Override
    public VideoPost updateTitle(String id, String title) {
        VideoPost videoPost = new VideoPost();
        videoPost.setVideoId(id);
        videoPost.setTitle(title);
        videoPostMapper.updateById(videoPost);
        return videoPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC, targetPojo = VideoPostBo.class)
    @Override
    public VideoPost updateIntroduction(String id, String introduction) {
        VideoPost videoPost = new VideoPost();
        videoPost.setVideoId(id);
        videoPost.setIntroduction(introduction);
        videoPostMapper.updateById(videoPost);
        return videoPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_VIDEO_UPSERT_TOPIC, targetPojo = VideoPostBo.class)
    @Override
    public VideoPost updateCoverImage(String id, String cover) {
        VideoPost videoPost = new VideoPost();
        videoPost.setVideoId(id);
        videoPost.setCoverImageUrl(cover);
        videoPostMapper.updateById(videoPost);
        return videoPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_VIDEO_DELETE_TOPIC, source = PayloadSource.PARAM)
    @Override
    public void deleteVideo(String id) {
        videoPostMapper.deleteById(id);
    }
}
