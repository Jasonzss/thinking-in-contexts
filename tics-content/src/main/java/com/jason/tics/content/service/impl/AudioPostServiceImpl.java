package com.jason.tics.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.jason.tics.api.content.bo.AudioPostBo;
import com.jason.tics.common.rocketmq.annotation.SendMessage;
import com.jason.tics.common.rocketmq.aop.PayloadSource;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.content.domain.AudioPost;
import com.jason.tics.content.domain.dto.AudioPostDto;
import com.jason.tics.content.mapper.AudioPostMapper;
import com.jason.tics.content.service.AudioPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class AudioPostServiceImpl implements AudioPostService {
    @Autowired
    private AudioPostMapper audioPostMapper;

    @Override
    public AudioPost getAudioPost(String audioId) {
        return audioPostMapper.selectById(audioId);
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC, targetPojo = AudioPostBo.class)
    @Override
    public AudioPost addAudioPost(AudioPostDto audioPostDto, long uid) {
        AudioPost audioPost = BeanUtil.copyProperties(audioPostDto, AudioPost.class);
        audioPost.setCreateTime(DateUtil.date());
        audioPost.setAuthorId(uid);
        audioPostMapper.insert(audioPost);
        return audioPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_AUDIO_DELETE_TOPIC, source = PayloadSource.PARAM)
    @Override
    public void deleteAudioPost(String audioId) {
        audioPostMapper.deleteById(audioId);
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC, targetPojo = AudioPostBo.class)
    @Override
    public AudioPost updateTitle(String audioId, String newTitle) {
        AudioPost audioPost = new AudioPost();
        audioPost.setAudioId(audioId);
        audioPost.setTitle(newTitle);
        audioPostMapper.updateById(audioPost);
        return audioPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC, targetPojo = AudioPostBo.class)
    @Override
    public AudioPost updateIntroduction(String audioId, String newIntroduction) {
        AudioPost audioPost = new AudioPost();
        audioPost.setAudioId(audioId);
        audioPost.setIntroduction(newIntroduction);
        audioPostMapper.updateById(audioPost);
        return audioPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC, targetPojo = AudioPostBo.class)
    @Override
    public AudioPost updateCoverImage(String audioId, String newCoverImage) {
        AudioPost audioPost = new AudioPost();
        audioPost.setAudioId(audioId);
        audioPost.setCoverImageUrl(newCoverImage);
        audioPostMapper.updateById(audioPost);
        return audioPost;
    }

    @SendMessage(topic = RocketMqConstant.CONTENT_AUDIO_UPSERT_TOPIC, targetPojo = AudioPostBo.class)
    @Override
    public AudioPost updateAudio(String audioId, String audio) {
        AudioPost audioPost = new AudioPost();
        audioPost.setAudioId(audioId);
        audioPost.setAudioUrl(audio);
        audioPostMapper.updateById(audioPost);
        return audioPost;
    }
}
