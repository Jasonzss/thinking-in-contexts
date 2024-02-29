package com.jason.tics.search.constant;

/**
 * @author Jason
 */
public class EsConstant {
    /**
     * 会做最粗粒度的拆分，推荐搜索时使用
     */
    public static final String IK_SMART_ANALYZER = "ik_smart";
    /**
     * 将文本做最细粒度的拆分，推荐建立索引时使用
     */
    public static final String IK_MAX_WORD_ANALYZER = "ik_max_word";

    /**
     * content通用字段
     */
    public static final String AUTHOR_ID = "authorId";
    public static final String TITLE = "title";
    public static final String QUANTITY_OF_VIEWS = "quantityOfViews";
    public static final String INTRODUCTION = "introduction";
    public static final String COVER_IMAGE_URL = "coverImageUrl";

    /**
     * video相关
     */
    public static final String VIDEO_INDEX = "video-index";
    public static final String VIDEO_URL = "videoUrl";
    public static final String VIDEO_ID = "videoId";


    /**
     * audio相关
     */
    public static final String AUDIO_INDEX = "audio-index";
    public static final String AUDIO_URL = "audioUrl";
    public static final String AUDIO_ID = "audioId";

    /**
     * essay相关
     */
    public static final String ESSAY_INDEX = "essay-index";
    public static final String ESSAY_URL = "essayUrl";
    public static final String ESSAY_ID = "essayId";

    public static final String CREATE_TIME = "createTime";
    public static final String UPDATE_TIME =  "updateTime";
}
