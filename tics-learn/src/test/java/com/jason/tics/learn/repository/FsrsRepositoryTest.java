package com.jason.tics.learn.repository;

import cn.hutool.core.date.DateUtil;
import com.jason.tics.api.learn.feign.FreeSpacedRepetitionSchedulerFeignClient;
import com.jason.tics.learn.LearnApplication;
import com.jason.tics.learn.domain.Cards;
import com.jason.tics.learn.domain.Fsrs;
import de.nickhansen.spacedrepetition.api.algorithm.FSRSAlgorithm;
import de.nickhansen.spacedrepetition.api.algorithm.fsrs.FSRSRating;
import de.nickhansen.spacedrepetition.api.algorithm.fsrs.FSRSState;
import de.nickhansen.spacedrepetition.api.algorithm.result.FSRSAlgorithmResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author Jason
 */
@SpringBootTest(classes = LearnApplication.class)
@Slf4j
public class FsrsRepositoryTest {
    @Autowired
    private FsrsRepository repository;
    @Autowired
    private CardsRepository cardsRepository;
    @Autowired
    private WordFsrsRepository wordFsrsRepository;

    private Cards cards;
    private Cards cards1;

    @Test
    public void test(){
        cards = new Cards("hello", 1L);
        cards1 = new Cards("fuck", 1L);
        cardsRepository.save(cards);
        cardsRepository.save(cards1);

        Fsrs fsrs = new Fsrs(cards.getCardUuid());
        Fsrs fsrs1 = new Fsrs(cards1.getCardUuid());
        fsrs1.setState(FreeSpacedRepetitionSchedulerFeignClient.REVIEW);
        repository.save(fsrs);
        repository.save(fsrs1);

        log.info("查找："+repository.findAllByUidAndState(1L, FreeSpacedRepetitionSchedulerFeignClient.NEW));
    }

    @Test
    public void dailyTest(){
        for (int i = 0; i < 1; i++) {
            Optional<Fsrs> byId = repository.findById("2c9770818d79974e018d7997795c0001");

            if (byId.isPresent()){
                Fsrs fsrs = byId.get();
                log.info(fsrs.toString());

                String[] s = {"HARD", "EASY", "HARD", "EASY", "EASY"};

                FSRSAlgorithmResult result = FSRSAlgorithm.builder().rating(FSRSRating.valueOf(s[i]))
                        .stability(fsrs.getStability())
                        .difficulty(fsrs.getDifficulty())
                        .elapsedDays(fsrs.getElapsedDays())
                        .repetitions(fsrs.getRepetitions())
                        .state(FSRSState.valueOf(fsrs.getState()))
                        .lastReview(fsrs.getLastReview())
                        .build().calc();

                Fsrs fsrs1 = new Fsrs(fsrs.getCardUuid(), result);
                fsrs1.setNextRepetition(DateUtil.yesterday());
                fsrs1.setLastReview(DateUtil.yesterday());
                repository.save(fsrs1);
            }else {
                log.info("not exist");
            }
        }
    }

    @Test
    public void test1(){
        log.info(wordFsrsRepository.findAll().toString());
    }
}
