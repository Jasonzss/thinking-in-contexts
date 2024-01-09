package com.jason.tics.content.service.impl;

import cn.hutool.core.map.MapUtil;
import com.jason.tics.content.domain.Tag;
import com.jason.tics.content.domain.VideoPost;
import com.jason.tics.content.domain.dto.VideoPostDto;
import com.jason.tics.content.mapper.TagMapper;
import com.jason.tics.content.mapper.VideoPostMapper;
import com.jason.tics.content.service.VideoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jason
 */
@Service
public class VideoPostServiceImpl implements VideoPostService {
    @Autowired
    private VideoPostMapper videoPostMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public VideoPost getVideo(String id) {
        VideoPost videoPost = videoPostMapper.selectById(id);
        List<Tag> tags = tagMapper.selectByMap(MapUtil.of("id", id));
        List<String> tagList = tags.stream().map(Tag::getTagName).collect(Collectors.toList());
        videoPost.setTags(tagList);
        return videoPost;
    }

    @Override
    public VideoPost addVideo(VideoPostDto videoPostDto) {
        VideoPost videoPost = new VideoPost();
        videoPost.setAuthorId(videoPostDto.getUid());
        videoPost.setTitle(videoPostDto.getTitle());
        videoPost.setVideoUrl(videoPostDto.getVideo());
        videoPost.setCoverImageUrl(videoPostDto.getCoverImage());
        videoPost.setIntroduction(videoPostDto.getIntroduction());
        videoPostMapper.insert(videoPost);
        List<Tag> tags = videoPostDto.getTags(videoPost.getVideoId());
        for (Tag tag : tags){
            tagMapper.insert(tag);
        }
        videoPost.setTags(videoPost.getTags());
        return videoPost;
    }

    @Override
    public VideoPost updateVideo(String id, String file) {
        VideoPost videoPost = new VideoPost();
        videoPost.setVideoId(id);
        videoPost.setVideoUrl(file);
        videoPostMapper.updateById(videoPost);
        return videoPost;
    }

    @Override
    public VideoPost updateTitle(String id, String title) {
        VideoPost videoPost = new VideoPost();
        videoPost.setVideoId(id);
        videoPost.setTitle(title);
        videoPostMapper.updateById(videoPost);
        return videoPost;
    }

    @Override
    public VideoPost updateIntroduction(String id, String introduction) {
        VideoPost videoPost = new VideoPost();
        videoPost.setVideoId(id);
        videoPost.setIntroduction(introduction);
        videoPostMapper.updateById(videoPost);
        return videoPost;
    }

    @Override
    public VideoPost updateCoverImage(String id, String cover) {
        VideoPost videoPost = new VideoPost();
        videoPost.setVideoId(id);
        videoPost.setCoverImageUrl(cover);
        videoPostMapper.updateById(videoPost);
        return videoPost;
    }

    @Override
    public void deleteVideo(String id) {
        videoPostMapper.deleteById(id);
    }
}
