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
 * @author Jason
 */
@Getter
@Setter
@ToString
@Entity
@Table
public class ChoiceQuestion extends BaseQuestion {

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
    private List<String> answers;

    @Override
    public Fraction answer(String... userAnswers){
        //判断是否单选题
        if(answers.size() == 1){
            //单选题
            if (userAnswers.length == 1 && answers.get(0).equals(userAnswers[0])){
                return Fraction.ONE;
            }else {
                return Fraction.ZERO;
            }
        }else{
            //多选题，答多不给分，答少给一半分
            if(userAnswers.length > answers.size()){
                return Fraction.ZERO;
            }else{
                boolean[] dic = new boolean[answers.size()];
                for (String s : userAnswers) {
                    if(dic[answers.indexOf(s)]){
                        //发现重复答案
                        throw new TicsException(ExceptionResponseEnum.INVALID_REQUEST_PARAM);
                    }else {
                        dic[answers.indexOf(s)] = true;
                    }
                }

                //所有回答均在答案范围
                return answers.size() == userAnswers.length ? Fraction.ONE : Fraction.ONE_HALF;
            }
        }
    }
}
