package com.jason.tics.content.service;

import com.jason.tics.content.domain.AudioText;

/**
 * @author Jason
 */
public interface AudioTextService {
    AudioText getAudioText(long tid);

    AudioText addAudioText(String id, String text);

    AudioText updateAudioText(String tid, String text);

    void deleteAudioText(long tid);
}
