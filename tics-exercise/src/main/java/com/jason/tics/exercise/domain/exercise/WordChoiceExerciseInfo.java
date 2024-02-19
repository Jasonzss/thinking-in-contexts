package com.jason.tics.exercise.domain.exercise;

import lombok.Data;

import java.util.*;

/**
 * 练习信息，不持久化，只存在于缓存中
 * @author Jason
 */
@Data
public class WordChoiceExerciseInfo {
    private Deque<WordTranslationChoiceExercise> unPassExercises;
    private Map<String, WordTranslationChoiceExercise> exercises;
    private Map<String, Integer> passTimes = new HashMap<>();
    private Map<String, Boolean> passed = new HashMap<>();

    public WordChoiceExerciseInfo() {
    }

    public WordChoiceExerciseInfo(List<WordTranslationChoiceExercise> exe) {
        addExercises(exe);
    }

    /**
     * 添加本次练习的题目
     */
    public void addExercises(List<WordTranslationChoiceExercise> exe){
        unPassExercises.addAll(exe);
        for (WordTranslationChoiceExercise exercise : exe) {
            exercises.put(exercise.getWord(), exercise);
        }
    }

    /**
     * 此次练习题是否通过
     */
    public void isExercisePassed(String word, boolean isPassed){
        passed.put(word, isPassed);
        passTimes.compute(word, (k,v) -> v == null ? v=1 : ++v);
        if(!isPassed){
            unPassExercises.addLast(exercises.get(word));
        }
    }

    /**
     * 获取下一个练习题
     */
    public WordTranslationChoiceExercise nextExercise(){
        return unPassExercises.pop();
    }
}
