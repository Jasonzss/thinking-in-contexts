package com.jason.tics.exercise.domain.exercise;

/**
 * @author Jason
 */
public interface WordTranslationChoiceExerciseSystem {
    /**
     * 获取单词选择练习
     * @param uid 请求的用户
     * @return 单词练习题
     */
    WordTranslationChoiceExercise nextWordExercise(long uid);

    /**
     * 提交单词选择练习的答案
     * @param uid 用户id
     * @param word 练习的单词
     * @param answer 回答
     * @return 是否正确
     */
    boolean pointWord(long uid, String word, String answer);

    /**
     * 完成本次练习，查看本次练习的成果
     * @param uid 用户id
     * @return 练习结果
     */
    WordChoiceExerciseInfo endExercise(long uid);
}
