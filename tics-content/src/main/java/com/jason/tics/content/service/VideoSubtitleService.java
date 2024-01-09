package com.jason.tics.content.service;

import com.jason.tics.content.domain.VideoSubtitle;

/**
 * @author Jason
 */
public interface VideoSubtitleService {
    VideoSubtitle getVideoSubtitle(long sid);

    void deleteVideoSubtitle(long sid);

    VideoSubtitle addVideoSubtitle(String id, String subtitle);

    VideoSubtitle updateVideoSubtitle(Long sid, String subtitle);
}
