package com.jason.tics.exercise.repository;

import com.jason.tics.exercise.ExerciseApplication;
import com.jason.tics.exercise.domain.exam.Scantron;
import com.jason.tics.exercise.domain.exam.PaperAnswer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jason
 */
@SpringBootTest(classes = ExerciseApplication.class)
@Slf4j
public class UserAnswerRepositoryTest {
    @Autowired
    private PaperAnswerRepository paperAnswerRepository;

    @Test
    public void test(){
        PaperAnswer paperAnswer = new PaperAnswer();
        paperAnswer.setId(1L);
        paperAnswer.setIdx(1);
        Scantron scantron = new Scantron();
        scantron.setId(1L);
        paperAnswer.setScantron(scantron);
        paperAnswer.setAnswer(new String[]{"回答"});
        paperAnswerRepository.save(paperAnswer);
    }
}
