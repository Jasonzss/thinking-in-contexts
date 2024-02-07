package com.jason.tics.translation.domain.youdao;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.jason.tics.api.translation.bo.TranslationResult;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.common.jpa.converter.ListConverter;
import com.jason.tics.common.jpa.converter.StringArrayConverter;
import com.jason.tics.common.jpa.converter.BooleanConverter;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason
 * @since 2023/09/11 - 1:25
 */
@Data
@Table(name = "youdao")
@Entity
public class YouDaoTextTranslationResponse {
    private int errorCode;      //	错误返回码
    @Id
    private String query;       //待翻译文本
    @Convert(converter = StringArrayConverter.class)
    private String[] translation;   //翻译后文本
    @Convert(converter = Basic.BasicConverter.class)
    @Column(columnDefinition = "text")
    private Basic basic;        //词义，基本词典，查词时才有
    @Convert(converter = ListConverter.class)
    private List<Web> web;       //网络释义
    private String l;           //源语言

    //添加 transient 代表其不需要被持久化
    @Transient
    private transient Map<String, String> dict;   //词典deeplink
    @Transient
    private transient Map<String, String> webdict;    //web deeplink
    private String tSpeakUrl;   //翻译后文本的语音url
    private String speakUrl;    //源文本的语音url
    @Convert(converter = StringArrayConverter.class)
    private String[] returnPhrase;  //单词校验后的结果，主要校验字母大小写、单词前含符号、中文简繁体

    private String requestId;
    @Transient
    private transient Map<String, String> mTerminalDict;
    @Convert(converter = BooleanConverter.class)
    private boolean isWord;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    @Data
    public static class Web{
        private String[] value;
        private String key;
    }

    @Data
    public static class Basic {
        private String phonetic;    //默认音标，默认是英式音标，英文查词成功，一定存在
        @Convert(converter = StringArrayConverter.class)
        private String[] explains;  //基本释义
        @Convert(converter = ListConverter.class)
        private List<String> exam_type; //所在考试范围 ["初中","高中","CET4","CET6","考研"]

        @SerializedName("us-phonetic")
        private String usPhonetic;
        @SerializedName("uk-phonetic")
        private String ukPhonetic;
        @SerializedName("us-speech")
        private String usSpeech;
        @SerializedName("uk-speech")
        private String ukSpeech;

        @Convert(converter = StringArrayConverter.class)
        private Wfs[] wfs;

        public static class BasicConverter implements AttributeConverter<Basic, String>{
            private final Gson gson = new Gson();

            @Override
            public String convertToDatabaseColumn(Basic attribute) {
                return gson.toJson(attribute);
            }

            @Override
            public Basic convertToEntityAttribute(String dbData) {
                return gson.fromJson(dbData, Basic.class);
            }
        }
    }

    @Data
    public static class Wfs{
        private Wf wf;
    }

    @Data
    public static class Wf{
        private String name;
        private String value;
    }

    public TranslationResult getWordTranslationResult(){
        if(!isWord){
            throw new TicsException(ExceptionResponseEnum.BAD_URL_PARAM);
        }

        TranslationResult res = new TranslationResult();
        res.setQuery(query);
        res.setTranslation(translation);
        res.setExplains(basic.explains);
        res.setStages(basic.exam_type);
        Map<String, String> wordForm = new HashMap<>();
        for (Wfs wf : basic.wfs) {
            wordForm.put(wf.wf.name, wf.wf.value);
        }
        res.setWordForm(wordForm);
        res.setUkPhonetic(basic.ukPhonetic);
        res.setUsPhonetic(basic.usPhonetic);
        Map<String,String[]> webTranslation = new HashMap<>();
        for (Web w : web) {
            webTranslation.put(w.key, w.value);
        }
        res.setWeb(webTranslation);
        res.setIsword(isWord);

        return res;
    }
}
