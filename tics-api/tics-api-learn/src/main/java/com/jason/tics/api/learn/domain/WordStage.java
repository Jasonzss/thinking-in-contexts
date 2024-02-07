package com.jason.tics.api.learn.domain;

/**
 * @author Jason
 */
public enum WordStage {
    /**
     * 新学的单词，不知道什么意思
     */
    NEW,
    /**
     * 常用单词，得心应手，几乎不可能忘
     */
    COMMON,
    /**
     * 比较简单，难不倒
     */
    EASY,
    /**
     * 思考一下，想的起来
     */
    THINK,
    /**
     * 有些困难，想不太起来
     */
    HARD,
    /**
     * 太难记住了
     */
    EXTREME
}
