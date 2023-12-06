package com.jason.tics.translation.domain.youdao;

import lombok.Data;

import java.util.Map;

/**
 * @author Jason
 * @since 2023/09/11 - 1:25
 */
@Data
public class YouDaoTextTranslationResponse {
    private String tSpeakUrl;//翻译后文本的语音url
    private String requestId;
    private String query;//待翻译文本
    private String[] translation;//翻译后文本
    private Map<String, String> mTerminalDict;
    private int errorCode;
    private Map<String, String> dict;
    private Map<String, String> webdict;
    private String l;//源语言
    private boolean isWord;
    private String speakUrl;//源文本的语音url



}
