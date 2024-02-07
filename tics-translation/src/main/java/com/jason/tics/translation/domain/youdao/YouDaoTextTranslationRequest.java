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
    private String q;   //待翻译文本
    @NotNull
    private String from;    //源语言
    @NotNull
    private String to;  //目标语言
    @NotNull
    private String appKey;  //应用ID
    @NotNull
    private String salt;    //随机字符串，可使用UUID进行生产
    @NotNull
    private String sign;    //签名，sha256(应用ID+input+salt+curtime+应用密钥)
    @NotNull
    private String signType;    //签名类型
    @NotNull
    private Timestamp curtime;  //当前UTC时间戳(秒)
    private String ext;     //翻译结果音频格式，支持mp3

    @Size(min = 0, max = 1)
    private int voice;  //翻译结果发音选择，0为女声，1为男声。默认为女声

    private boolean strict; //是否严格按照指定from和to进行翻译：true/false, 如果为false，则会自动中译英，英译中。默认为false
    private String vocabid; //用户上传的词典
    private String domain;  //领域化翻译，有：general-通用、computers-计算机、medicine-医学、finance-金融经济、game-游戏
    private boolean rejectFallback; //拒绝领域化翻译降级-当领域化翻译失败时改为通用翻译
}
