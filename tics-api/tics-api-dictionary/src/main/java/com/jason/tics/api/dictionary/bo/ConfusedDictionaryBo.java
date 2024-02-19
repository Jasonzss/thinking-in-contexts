package com.jason.tics.api.dictionary.bo;

import lombok.Data;

import java.util.List;

/**
 * @author Jason
 */
@Data
public class ConfusedDictionaryBo {
    private String target;
    private String usPhonetic;
    private String ukPhonetic;
    private String usSpeech;
    private String ukSpeech;
    private String[] translation;
    private List<String> similarity;
    private List<String> synonyms;
    private List<String> variants;
}
