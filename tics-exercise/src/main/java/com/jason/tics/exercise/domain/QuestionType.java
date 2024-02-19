package com.jason.tics.exercise.domain;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.exercise.repository.ChoiceQuestionRepository;
import com.jason.tics.exercise.repository.FillBlankQuestionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 * 答题类型
 */
public enum QuestionType {

    /**
     * 从业务的角度来区分题目类型
     */
    LISTENING_CHOICE("lic", "听力选择题", null),
    LISTENING_FILL("lif", "听力填空题", null),
    CHOICE("cho", "选择题", ChoiceQuestionRepository.class),
    WORD_CHOICE("woc", "选词填空", null),
    CLOZE("clo", "完型填空", null),
    READ("rea", "阅读题", null),
    READ_AND_MATCH("ram", "匹配阅读", null),
    FILL_BLANK("fil", "填空题", FillBlankQuestionRepository.class),
    CORRECTION("cor", "纠错题", null),
    TRANSLATION("tra", "翻译", null),
    COMPOSITION("com", "作文", null);

    public static final Integer PREFIX_LENGTH = 3;

    /**
     * 用于生成id的前缀
     */
    private String prefix;
    /**
     * 题目类型名
     */
    private String name;

    private Class<? extends JpaRepository<? extends BaseQuestion, String>> repository;

    QuestionType(String prefix, String name,
                 Class<? extends JpaRepository<? extends BaseQuestion, String>> repository) {
        this.prefix = prefix;
        this.name = name;
        this.repository = repository;
    }

    public static QuestionType getQuestionTypeByPrefix(String prefix){
        for (QuestionType value : QuestionType.values()) {
            if (value.getPrefix().equals(prefix)) {
                return value;
            }
        }

        throw new TicsException(ExceptionResponseEnum.QUESTION_NOT_EXIST);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends JpaRepository<? extends BaseQuestion, String>> getRepository() {
        return repository;
    }

    public void setRepository(Class<? extends JpaRepository<? extends BaseQuestion, String>> repository) {
        this.repository = repository;
    }
}
