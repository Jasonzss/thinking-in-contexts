package com.jason.tics.content.service.impl;

import com.jason.tics.content.domain.VideoSubtitle;
import com.jason.tics.content.mapper.VideoSubtitleMapper;
import com.jason.tics.content.service.VideoSubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class VideoSubtitleServiceImpl implements VideoSubtitleService {
    @Autowired
    private VideoSubtitleMapper videoSubtitleMapper;

    @Override
    public VideoSubtitle getVideoSubtitle(long sid) {
        return videoSubtitleMapper.selectById(sid);
    }

    @Override
    public void deleteVideoSubtitle(long sid) {
        videoSubtitleMapper.deleteById(sid);
    }

    @Override
    public VideoSubtitle addVideoSubtitle(String id, String subtitle) {
        VideoSubtitle videoSubtitle = new VideoSubtitle();
        videoSubtitle.setVideoId(id);
        videoSubtitle.setSubtitleUrl(subtitle);
        videoSubtitleMapper.insert(videoSubtitle);
        return videoSubtitle;
    }

    @Override
    public VideoSubtitle updateVideoSubtitle(Long sid, String subtitle) {
        VideoSubtitle videoSubtitle = new VideoSubtitle();
        videoSubtitle.setVideoSubtitleId(sid);
        videoSubtitle.setSubtitleUrl(subtitle);
        videoSubtitleMapper.updateById(videoSubtitle);
        return videoSubtitle;
    }
}
