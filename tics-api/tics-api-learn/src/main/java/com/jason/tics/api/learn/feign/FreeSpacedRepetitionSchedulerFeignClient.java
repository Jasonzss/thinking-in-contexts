package com.jason.tics.api.learn.feign;

import com.jason.tics.api.learn.domain.WordLearningResult;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Jason
 */
public interface FreeSpacedRepetitionSchedulerFeignClient {
    /**
     * 忘记;错误的答案
     */
    Integer AGAIN = 0;
    /**
     * 提醒;检索到正确答案有一定难度
     */
    Integer HARD = 1;
    /**
     * 犹豫后正确答案
     */
    Integer GOOD = 2;
    /**
     * 完美的答案
     */
    Integer EASY = 3;

    /**
     * 学习or复习单词
     * @param uid 用户id
     * @param word 被学习的词汇
     * @param rating 记忆等级，在学新单词的情况下可以为空
     */
    ServerResponseEntity<WordLearningResult> learnWordWithRating(@NotNull long uid, @NotNull String word, @NotNull @Range(min = 0, max = 3) int rating);

    /**
     * 批量添加新学单词
     * @param uid 用户id
     * @param word 加入学习计划的单词数组
     */
    ServerResponseEntity<List<WordLearningResult>> addWordSchedule(long uid, List<String> word);

    /**
     * 没学过
     */
    String NEW = "NEW";
    /**
     * 最近第一次学习
     */
    String LEARNING = "LEARNING";
    /**
     * 完成学习，复习状态时
     */
    String REVIEW = "REVIEW";
    /**
     * 在 REVIEW 状态下被遗忘
     */
    String RELEARNING = "RELEARNING";

    /**
     * 综合获取用户的 0 1 2 3 状态下的单词
     * @param uid 用户id
     */
    ServerResponseEntity<List<WordLearningResult>> listLearningWords(long uid);
}
