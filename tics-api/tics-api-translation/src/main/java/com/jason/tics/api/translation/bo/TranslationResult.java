package com.jason.tics.api.translation.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class TranslationResult {
    private String query;
    private String[] translation;
    private String[] explains;
    private Map<String,String[]> web;
    private List<String> stages;
    /**
     * 形如  复数：texts
     */
    private Map<String,String> wordForm;
    private String usPhonetic;
    private String ukPhonetic;
    private String usSpeech;
    private String ukSpeech;
    private Boolean isword;

    public boolean isWord(){
        return isword;
    }
}
