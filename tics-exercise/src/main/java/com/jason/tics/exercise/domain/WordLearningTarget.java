package com.jason.tics.exercise.domain;

import lombok.Data;

import javax.persistence.*;
/**
 * @author Jason
 */
@Data
@Entity
@Table
public class WordLearningTarget {
    @Id
    private Long uid;
    private Integer wordNumPerExercise;
}
