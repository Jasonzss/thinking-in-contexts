package com.jason.tics.exercise.domain;

/**
 * @author Jason
 * 答题类型
 */
public enum QuestionType {
    /**
     * 从业务的角度来区分题目类型
     */
    LISTENING_CHOICE("lic", "听力选择题"),
    LISTENING_FILL("lif", "听力填空题"),
    CHOICE("cho", "选择题"),
    WORD_CHOICE("woc", "选词填空"),
    CLOZE("clo", "完型填空"),
    READ("rea", "阅读题"),
    READ_AND_MATCH("ram", "匹配阅读"),
    FILL_BLANK("fil", "填空题"),
    CORRECTION("cor", "纠错题"),
    TRANSLATION("tra", "翻译"),
    COMPOSITION("com", "作文");

    /**
     * 用于生成id的前缀
     */
    private String prefix;
    /**
     * 题目类型名
     */
    private String name;

    QuestionType(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
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
}
