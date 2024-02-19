package com.jason.tics.exercise.domain.exam;

import lombok.Data;

/**
 * @author Jason
 */
@Data
public class ExaminationInfo {
    private Long scantronId;
    private Long uid;
    private Long paperId;
    /**
     * 本次考试继续的开始时间
     */
    private Long continueTime;
    /**
     * 已经用了的时间
     */
    private Long usedTime;
    /**
     * 考试限时
     */
    private Long timeLimit;
}
