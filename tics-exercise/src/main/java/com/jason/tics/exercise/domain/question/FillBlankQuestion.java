package com.jason.tics.exercise.domain.question;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.common.jpa.converter.ListConverter;
import com.jason.tics.exercise.domain.BaseQuestion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.math.Fraction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

/**
 * 填空题，答案可以有多种且多种之间使用中文分号隔开
 * @author Jason
 */
@Getter
@Setter
@ToString
@Entity
@Table
public class FillBlankQuestion extends BaseQuestion {
    /**
     * 题目
     */
    @Length(max = 300)
    private String content;

    /**
     * 选项
     */
    @Convert(converter = ListConverter.class)
    @Length(max = 300)
    private List<String> choices;

    /**
     * 正确选项
     */
    @Convert(converter = ListConverter.class)
    @Length(max = 50)
    private List<String> answers;

    public static final transient String ANSWER_SEPARATOR = "；";

    /**
     * 回答填空题
     */
    @Override
    public Fraction answer(String... userAnswers) {
        int correct = 0;
        if (userAnswers == null || userAnswers.length != answers.size()){
            throw new TicsException(ExceptionResponseEnum.INVALID_REQUEST_PARAM);
        }
        for(int i = 0; i < answers.size(); i++){
            if (check(answers.get(i), userAnswers[i])) {
                correct++;
            }
        }
        return Fraction.getFraction(correct, answers.size());
    }

    private boolean check(String answer, String userAnswer){
        if (answer.length() == userAnswer.length() && userAnswer.equals(answer)){
            return true;
        }

        String[] ans = answer.split(ANSWER_SEPARATOR);
        for (String an : ans) {
            if (an.trim().equals(userAnswer)) {
                return true;
            }
        }

        return false;
    }
}
