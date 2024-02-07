package com.jason.tics.dictionary.domain.vo;

import com.jason.tics.dictionary.domain.DomainTranslation;
import com.jason.tics.dictionary.domain.Example;
import com.jason.tics.dictionary.domain.Phrase;
import com.jason.tics.dictionary.domain.WordBook;
import com.jason.tics.dictionary.domain.web.WebTranslation;
import com.jason.tics.dictionary.domain.extension.Etymology;
import com.jason.tics.dictionary.domain.extension.Similarity;
import com.jason.tics.dictionary.domain.extension.Synonym;
import com.jason.tics.dictionary.domain.extension.Variant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class DictionaryVo {
    private String target;
    private String usPhonetic;
    private String ukPhonetic;
    private String usSpeech;
    private String ukSpeech;
    private List<String> stage;
    private String[] translation;
    private List<DomainTranslation> domainTranslations;
    private List<WebTranslation> webTranslation;
    private List<WordBook> wordBook;
    private Page<Phrase> phrase;
    private Page<Example> example;
    private List<Similarity> similarity;
    private List<Synonym> synonyms;
    private List<Variant> variants;
    private Etymology etymology;
}
