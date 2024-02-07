package com.jason.tics.learn.test;

import cn.hutool.core.date.DateUtil;
import com.jason.tics.learn.LearnApplication;
import de.nickhansen.spacedrepetition.api.algorithm.FSRSAlgorithm;
import de.nickhansen.spacedrepetition.api.algorithm.SM2Algorithm;
import de.nickhansen.spacedrepetition.api.algorithm.fsrs.FSRSRating;
import de.nickhansen.spacedrepetition.api.algorithm.fsrs.FSRSState;
import de.nickhansen.spacedrepetition.api.algorithm.result.FSRSAlgorithmResult;
import de.nickhansen.spacedrepetition.api.algorithm.result.SM2AlgorithmResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jason
 */
@SpringBootTest(classes = LearnApplication.class)
@Slf4j
public class SpacedRepetitionTest {
    @Test
    public void testFSRSAlgorithm(){
        FSRSAlgorithmResult result = FSRSAlgorithm.builder()
                .elapsedDays(2)  //上次学习和下次学习之间的天数 default:0
                .rating(FSRSRating.AGAIN)   //记忆等级，用户输入对知识点的记忆的程度 default:0
                .scheduledDays(2)    //几天后复习 default:0，可不填
                .stability(1.2f)    //稳定性 default:0

                //会返回的值
                .lastReview(DateUtil.current())   //上一次学习知识点的时间 default:0
                .difficulty(2)   //难度 default:0
                .repetitions(2)  //复习的次数 default:0
                .state(FSRSState.LEARNING)    //学习阶段 default:NEW
                .build().calc();

        log.info(result.toString());
    }

    @Test
    public void testSM2Algorithm(){
        // Create the algorithm object
        SM2AlgorithmResult result = SM2Algorithm.builder()
                .quality(3)     //评估学习内容的质量 default:0
                .interval(5)    //上次使用的间隔 default:0

                //会返回的值
                .repetitions(4) //复习次数 default:0
                .easinessFactor(1.6F)   //前一次的难度因子 default:2.5
                .build().calc();

        // Calculate the outputs based on the inputs from object creation
        log.info(result.toString());
    }
}
