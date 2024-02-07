package com.jason.tics.exercise.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.tics.exercise.ExerciseApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jason
 */
@SpringBootTest(classes = ExerciseApplication.class)
@Slf4j
public class AnswerSheetTest {
    @Autowired
    private AnswerSheetRepository answerSheetRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws JsonProcessingException {
        log.info("查出所有的答题卡："+objectMapper.writeValueAsString(answerSheetRepository.findById(1L)));
    }
}
