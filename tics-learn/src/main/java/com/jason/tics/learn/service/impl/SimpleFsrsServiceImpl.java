package com.jason.tics.learn.service.impl;

import com.jason.tics.api.learn.domain.WordLearningResult;
import com.jason.tics.api.learn.domain.WordStage;
import com.jason.tics.learn.domain.Cards;
import com.jason.tics.learn.domain.Fsrs;
import com.jason.tics.learn.domain.pojo.WordFsrs;
import com.jason.tics.learn.repository.CardsRepository;
import com.jason.tics.learn.repository.FsrsRepository;
import com.jason.tics.learn.repository.WordFsrsRepository;
import com.jason.tics.learn.service.FsrsService;
import de.nickhansen.spacedrepetition.api.algorithm.FSRSAlgorithm;
import de.nickhansen.spacedrepetition.api.algorithm.fsrs.FSRSRating;
import de.nickhansen.spacedrepetition.api.algorithm.fsrs.FSRSState;
import de.nickhansen.spacedrepetition.api.algorithm.result.FSRSAlgorithmResult;
import org.apache.commons.lang3.math.Fraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Jason
 */
@Service
public class SimpleFsrsServiceImpl implements FsrsService {
    @Autowired
    private WordFsrsRepository wordFsrsRepository;
    @Autowired
    private FsrsRepository fsrsRepository;
    @Autowired
    private CardsRepository cardsRepository;

    /**
     * 列出单词和对应的Stage，判断的规则比较简单
     * 使用fsrs算法得到的 stability、difficulty、repetition、state四个字段去判断
     *
     * 测试阶段数据不足时使用，后续有待优化
     *
     * @return 学习中的单词和对应学习阶段的映射
     */
    @Override
    public List<WordLearningResult> listWordsStage(long uid) {
        List<WordFsrs> allByUid = wordFsrsRepository.findAllByUid(uid);
        return getWordLearningResult(allByUid);
    }

    @Override
    public List<WordLearningResult> addWordSchedule(long uid, List<String> word) {
        Set<Cards> cardsSet = new HashSet<>();
        Set<Fsrs> fsrsSet = new HashSet<>();
        for (String s : word) {
            cardsSet.add(new Cards(s, uid));
        }

        List<Cards> cards = cardsRepository.saveAll(cardsSet);
        for (Cards card : cards) {
            fsrsSet.add(new Fsrs(card.getCardUuid()));
        }
        fsrsRepository.saveAllAndFlush(fsrsSet);
        return getWordLearningResult(wordFsrsRepository.findAllByFront(word));
    }

    @Override
    public WordLearningResult learnWordWithRating(long uid, String word, int rating) {
        Optional<Cards> cardsOp = cardsRepository.findByFrontAndUid(word, uid);
        Fsrs fsrs;
        if (cardsOp.isPresent()) {
            //复习
            fsrs = fsrsRepository.getById(cardsOp.get().getCardUuid());
        }else{
            //新学
            Cards savedCards = cardsRepository.save(new Cards(word, uid));
            fsrs = new Fsrs(savedCards.getCardUuid());
        }

        FSRSAlgorithmResult result = FSRSAlgorithm.builder()
                .rating(FSRSRating.values()[rating])
                .stability(fsrs.getStability())
                .difficulty(fsrs.getDifficulty())
                .elapsedDays(fsrs.getElapsedDays())
                .repetitions(fsrs.getRepetitions())
                .state(FSRSState.valueOf(fsrs.getState()))
                .lastReview(fsrs.getLastReview())
                .build().calc();

        Fsrs f = new Fsrs(fsrs.getCardUuid(), result);
        fsrsRepository.save(f);
        return new WordLearningResult(fsrs.getCardUuid(), word, judgeStage(f));
    }

    private WordStage judgeStage(Fsrs fsrs){
        String state = fsrs.getState();
        if (state.equals(FSRSState.NEW.name()) || state.equals(FSRSState.LEARNING.name())) {
            return WordStage.NEW;
        }
        if(state.equals(FSRSState.RELEARNING.name())){
            return WordStage.THINK;
        }

        int repetitions = fsrs.getRepetitions();
        float difficulty = fsrs.getDifficulty();
        float reciprocalOfStability = 1/fsrs.getStability();

        if(reciprocalOfStability < Fraction.getFraction(1,20).floatValue()){
            return WordStage.COMMON;
        }
        if(reciprocalOfStability < Fraction.getFraction(1, 12).floatValue()
                && difficulty < 4 && repetitions < 20){
            return WordStage.EASY;
        }
        if(reciprocalOfStability < Fraction.getFraction(1, 4).floatValue()
                && difficulty < 8 && repetitions < 60){
            return WordStage.THINK;
        }
        if(reciprocalOfStability < 1 && difficulty < 12 && repetitions < 80){
            return WordStage.HARD;
        }

        return WordStage.EXTREME;
    }

    private List<WordLearningResult> getWordLearningResult(List<WordFsrs> wordFsrsList){
        List<WordLearningResult> wordLearningResults = new ArrayList<>();
        for (WordFsrs wordFsrs : wordFsrsList) {
            wordLearningResults.add(new WordLearningResult(wordFsrs.getCardUuid(),
                    wordFsrs.getFront(), judgeStage(wordFsrs.getFsrs())));
        }
        return wordLearningResults;
    }
}
