package com.jason.tics.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.jason.tics.content.domain.AudioPost;
import com.jason.tics.content.domain.Tag;
import com.jason.tics.content.domain.dto.AudioPostDto;
import com.jason.tics.content.mapper.AudioPostMapper;
import com.jason.tics.content.mapper.TagMapper;
import com.jason.tics.content.service.AudioPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jason
 */
@Service
public class AudioPostServiceImpl implements AudioPostService {
    @Autowired
    private AudioPostMapper audioPostMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public AudioPost getAudioPost(String audioId) {
        AudioPost audioPost = audioPostMapper.selectById(audioId);
        List<Tag> tags = tagMapper.selectByMap(MapUtil.of("id", audioId));
        List<String> tagList = tags.stream().map(Tag::getTagName).collect(Collectors.toList());
        audioPost.setTags(tagList);
        return audioPost;
    }

    @Override
    public AudioPost addAudioPost(AudioPostDto audioPostDto, long uid) {
        AudioPost audioPost = BeanUtil.copyProperties(audioPostDto, AudioPost.class);
        audioPost.setCreateTime(DateUtil.date());
        audioPost.setAuthorId(uid);
        audioPostMapper.insert(audioPost);
        return audioPost;
    }

    @Override
    public void deleteAudioPost(String audioId) {
        audioPostMapper.deleteById(audioId);
    }

    @Override
    public AudioPost updateTitle(String audioId, String newTitle) {
        AudioPost audioPost = new AudioPost();
        audioPost.setAudioId(audioId);
        audioPost.setTitle(newTitle);
        audioPostMapper.updateById(audioPost);
        return audioPost;
    }

    @Override
    public AudioPost updateIntroduction(String audioId, String newIntroduction) {
        AudioPost audioPost = new AudioPost();
        audioPost.setAudioId(audioId);
        audioPost.setIntroduction(newIntroduction);
        audioPostMapper.updateById(audioPost);
        return audioPost;
    }

    @Override
    public AudioPost updateCoverImage(String audioId, String newCoverImage) {
        AudioPost audioPost = new AudioPost();
        audioPost.setAudioId(audioId);
        audioPost.setCoverImageUrl(newCoverImage);
        audioPostMapper.updateById(audioPost);
        return audioPost;
    }

    @Override
    public AudioPost updateAudio(String audioId, String audio) {
        AudioPost audioPost = new AudioPost();
        audioPost.setAudioId(audioId);
        audioPost.setAudioUrl(audio);
        audioPostMapper.updateById(audioPost);
        return audioPost;
    }
}
