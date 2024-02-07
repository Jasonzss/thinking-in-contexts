package com.jason.tics.dictionary.service.impl;

import com.jason.tics.dictionary.domain.extension.Stage;
import com.jason.tics.dictionary.domain.extension.StageWord;
import com.jason.tics.dictionary.repository.StageRepository;
import com.jason.tics.dictionary.repository.StageWordRepository;
import com.jason.tics.dictionary.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 */
@Service
public class StageServiceImpl implements StageService {
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageWordRepository stageWordRepository;

    @Override
    public List<StageWord> addStageWord(List<String> stageNames, String word) {
        List<StageWord> stageWords = new ArrayList<>();

        for (String stageName : stageNames) {
            if (!stageRepository.existsById(stageName)) {
                stageRepository.save(new Stage(stageName));
            }
            stageWords.add(new StageWord(stageName, word));
        }

        return stageWordRepository.saveAllAndFlush(stageWords);
    }
}
