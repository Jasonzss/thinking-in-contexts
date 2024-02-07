package com.jason.tics.exercise.resource;

import com.jason.tics.exercise.domain.ExamSystem;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jason
 */
@RequestMapping("/exercise")
public class ExerciseResource {
    public ExamSystem getExercise(long uid, String... questionType){
        return null;
    }
}
