package com.jason.tics.recommend.domain;

/**
 * @author Jason
 */
public enum Preferences {
    CRAZY("痴迷",10f),
    FEVER("狂热;热爱", 8f),
    LIKE("喜欢",6f),
    INTERESTING("感兴趣",5f),
    NORMAL("一般",3f),
    DISLIKE("不喜欢",1f),
    DISGUST("厌恶",0f)
    ;

    String name;
    /**
     * 喜爱程度，分值在 0~10
     */
    Float value;

    Preferences(String name, Float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Float getValue() {
        return value;
    }
}
