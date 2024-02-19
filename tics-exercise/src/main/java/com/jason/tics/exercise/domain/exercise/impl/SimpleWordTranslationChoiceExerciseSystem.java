package com.jason.tics.exercise.domain.exercise.impl;

import cn.hutool.core.util.RandomUtil;
import com.jason.tics.api.dictionary.bo.ConfusedDictionaryBo;
import com.jason.tics.api.dictionary.feign.DictionaryFeignClient;
import com.jason.tics.api.learn.domain.WordLearningResult;
import com.jason.tics.api.learn.feign.FreeSpacedRepetitionSchedulerFeignClient;
import com.jason.tics.api.translation.feign.TranslationFeignClient;
import com.jason.tics.common.cache.constant.ExerciseCacheNames;
import com.jason.tics.common.cache.service.RedisCacheService;
import com.jason.tics.exercise.domain.exercise.*;
import com.jason.tics.exercise.event.WordTranslationChoiceExerciseEndUpEvent;
import com.jason.tics.exercise.repository.WordLearningTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Jason
 */
@Component
public class SimpleWordTranslationChoiceExerciseSystem implements WordTranslationChoiceExerciseSystem {
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private FreeSpacedRepetitionSchedulerFeignClient freeSpacedRepetitionSchedulerFeignClient;
    @Autowired
    private WordLearningTargetRepository wordLearningTargetRepository;
    @Autowired
    private DictionaryFeignClient dictionaryFeignClient;
    @Autowired
    private TranslationFeignClient translationFeignClient;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public static final Long DEFAULT_EXERCISE_EXPIRE_TIME = 1000*60*60L;

    public static final int DEFAULT_CHOICES_NUMBER = 4;

    @Override
    public WordTranslationChoiceExercise nextWordExercise(long uid) {
        WordChoiceExerciseInfo info;
        if (redisCacheService.find(ExerciseCacheNames.WORD_CHOICE_EXERCISE, uid).isPresent()) {
            //判断是否已有开启的练习
            info = redisCacheService.get(ExerciseCacheNames.WORD_CHOICE_EXERCISE, uid);
        }else{
            //开启新的练习
            //获取用户的学习情况
            WordLearningTarget target = wordLearningTargetRepository.findById(uid)
                    .orElse(WordLearningTarget.defaultTarget(uid, wordLearningTargetRepository));

            SortedSet<WordLearningResult> results =
                    freeSpacedRepetitionSchedulerFeignClient.listUserLearningWords(uid).getData();

            //根据用户学习信息出题
            List<String> words = new ArrayList<>();
            Iterator<WordLearningResult> iterator = results.iterator();
            for (int i = 0; i < target.getWordNumPerExercise(); i++) {
                if (iterator.hasNext()) {
                    words.add(iterator.next().getWord());
                }
            }

            List<WordTranslationChoiceExercise> exercises = generateExercise(words);
            info = new WordChoiceExerciseInfo(exercises);
            //习题放入缓存，且默认两小时后过期
            redisCacheService.set(ExerciseCacheNames.WORD_CHOICE_EXERCISE, uid, info, DEFAULT_EXERCISE_EXPIRE_TIME);
        }

        return info.nextExercise();
    }

    private List<WordTranslationChoiceExercise> generateExercise(List<String> words){
        List<WordTranslationChoiceExercise> exercises = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            WordTranslationChoiceExercise exercise = new WordTranslationChoiceExercise();
            exercise.setWord(word);

            ConfusedDictionaryBo confusedDictionary =
                    dictionaryFeignClient.getConfusedDictionary(word);
            exercise.setRightAnswer(confusedDictionary.getTranslation()[0]);
            exercise.setSpeech(confusedDictionary.getUsSpeech());

            List<String> similarity = confusedDictionary.getSimilarity();

            //填充其他选项
            for (String s : similarity) {
                exercise.addChoice(translationFeignClient.translate(s, null).getData().getTranslation()[0]);
            }
            boolean[] used = new boolean[words.size()];
            used[i] = true;
            for(int j = exercise.getChoices().size(); j <= DEFAULT_CHOICES_NUMBER; j++){
                exercise.addChoice(translationFeignClient
                        .translate(words.get(getRandom(used)), null).getData().getTranslation()[0]);
            }

            exercises.add(exercise);
        }
        return exercises;
    }

    private int getRandom(boolean[] arr){
        int i = RandomUtil.randomInt(0, arr.length);

        while(arr[i]){
            i = RandomUtil.randomInt(0, arr.length);
        }

        arr[i] = true;
        return i;
    }

    @Override
    public boolean pointWord(long uid, String word, String answer) {
        WordChoiceExerciseInfo info = redisCacheService.get(ExerciseCacheNames.WORD_CHOICE_EXERCISE, uid);
        boolean passed = info.getExercises().get(word).point(answer);
        info.isExercisePassed(word, passed);
        return passed;
    }

    /**
     * 手动结束练习
     */
    @Override
    public WordChoiceExerciseInfo endExercise(long uid) {
        WordChoiceExerciseInfo info = redisCacheService.get(ExerciseCacheNames.WORD_CHOICE_EXERCISE, uid);
        eventPublisher.publishEvent(new WordTranslationChoiceExerciseEndUpEvent(this, info));
        return info;
    }
}
