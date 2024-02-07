package com.jason.tics.learn.domain;

import cn.hutool.core.date.DateUtil;
import com.jason.tics.api.learn.feign.FreeSpacedRepetitionSchedulerFeignClient;
import de.nickhansen.spacedrepetition.api.algorithm.result.FSRSAlgorithmResult;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * https://github.com/open-spaced-repetition/free-spaced-repetition-scheduler/blob/main/README_CN.md
 *
 * @see de.nickhansen.spacedrepetition.api.algorithm.result.FSRSAlgorithmResult
 * @author Jason
 */
@Data
@Entity
@Table
public class Fsrs {
    /**
     * 学习卡片的id
     */
    @Id
    private String cardUuid;
    /**
     * 稳定性
     *  稳定性指的是记忆的存储强度。越高，记得越牢，记忆遗忘得越慢
     */
    private Float stability;
    /**
     * 难度
     */
    private Float difficulty;
    /**
     * 上次学习和下次学习之间的天数
     */
    private Integer elapsedDays;
    /**
     * 复习的次数
     */
    private Integer repetitions;
    /**
     * 学习阶段
     */
    private String state;
    /**
     * 复习间隔天数
     */
    private Integer dayInterval;
    /**
     * 下一次复习的时间
     */
    private Date nextRepetition;
    /**
     * 上一次学习知识点的时间
     * 默认为0，使用算法后有返回值，覆盖即可
     */
    private Date lastReview;

    public Fsrs() {
    }
    public Fsrs(String cardUuid) {
        this.cardUuid = cardUuid;
        //设置默认值
        this.stability = 0f;
        this.difficulty = 0f;
        this.elapsedDays = 0;
        this.repetitions = 0;
        this.state = FreeSpacedRepetitionSchedulerFeignClient.NEW;
        this.dayInterval = 0;
    }

    public Fsrs(String cardUuid, FSRSAlgorithmResult result) {
        this.cardUuid = cardUuid;

        this.repetitions = result.getRepetitions();
        this.difficulty = result.getDifficulty();
        this.stability = result.getStability();
        this.elapsedDays = result.getElapsedDays();
        this.state = result.getState().toString();
        this.dayInterval = result.getInterval();
        this.nextRepetition = DateUtil.date(result.getNextRepetitionTime());
        this.lastReview = DateUtil.date(result.getLastReview());
    }

    public long getNextRepetition() {
        return nextRepetition == null ? 0 : nextRepetition.getTime();
    }

    public long getLastReview() {
        return lastReview == null ? 0 : lastReview.getTime();
    }
}
