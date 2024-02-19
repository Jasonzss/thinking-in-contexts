package com.jason.tics.exercise.domain.exercise;

import com.jason.tics.exercise.repository.WordLearningTargetRepository;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * @author Jason
 */
@Data
@Entity
@Table
public class WordLearningTarget {
    public static final Integer DEFAULT_DAILY_WORD_NUMBER = 10;

    @Id
    private Long uid;
    @Min(5)
    private Integer wordNumPerExercise;

    public static WordLearningTarget defaultTarget(long uid, WordLearningTargetRepository repository){
        WordLearningTarget target = new WordLearningTarget();
        target.setUid(uid);
        target.setWordNumPerExercise(DEFAULT_DAILY_WORD_NUMBER);
        return repository.save(target);
    }
}
