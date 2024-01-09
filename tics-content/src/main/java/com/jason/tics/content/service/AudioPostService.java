package com.jason.tics.content.service;

import com.jason.tics.content.domain.AudioPost;
import com.jason.tics.content.domain.dto.AudioPostDto;

/**
 * @author Jason
 */
public interface AudioPostService {
    AudioPost getAudioPost(String audioId);

    AudioPost addAudioPost(AudioPostDto audioPostDto, long uid);

    void deleteAudioPost(String audioId);

    AudioPost updateTitle(String audioId, String newTitle);

    AudioPost updateIntroduction(String audioId, String newIntroduction);

    AudioPost updateCoverImage(String audioId, String newCoverImage);

    AudioPost updateAudio(String audioId, String audio);
}
