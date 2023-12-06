package com.jason.tics.translation.domain.youdao;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * @author Jason
 * @since 2023/09/11 - 17:52
 */
@Data
public class YouDaoTextTranslationRequest {
    @NotNull
    private String q;
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private String appKey;
    @NotNull
    private String salt;
    @NotNull
    private String sign;
    @NotNull
    private String signType;
    @NotNull
    private Timestamp curtime;
    private String ext;

    /** 0为女生，1为男生 */
    @Size(max = 1)
    private int voice;
    private boolean strict;
    private String vocabid;
    private String domain;
    private boolean rejectFallback;
}
