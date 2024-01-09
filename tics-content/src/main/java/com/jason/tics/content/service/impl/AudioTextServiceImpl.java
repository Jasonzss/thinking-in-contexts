package com.jason.tics.content.service.impl;

import com.jason.tics.content.domain.AudioText;
import com.jason.tics.content.mapper.AudioTextMapper;
import com.jason.tics.content.service.AudioTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class AudioTextServiceImpl implements AudioTextService {
    @Autowired
    private AudioTextMapper audioTextMapper;

    @Override
    public AudioText getAudioText(long tid) {
        return audioTextMapper.selectById(tid);
    }

    @Override
    public AudioText addAudioText(String id, String text) {
        AudioText audioText = new AudioText(id, text);
        audioTextMapper.insert(audioText);
        return audioText;
    }

    @Override
    public AudioText updateAudioText(String tid, String text) {
        AudioText audioText = new AudioText(tid, text);
        audioTextMapper.updateById(audioText);
        return audioText;
    }

    @Override
    public void deleteAudioText(long tid) {
        audioTextMapper.deleteById(tid);
    }
}
