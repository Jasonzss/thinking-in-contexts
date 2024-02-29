package com.jason.tics.common.rocketmq.constant;

/**
 * @author Jason
 */
public class RocketMqConstant {

    /**
     * 默认的发送消息超时时间
     */
    public static long TIMEOUT = 3000;


    /**
     * 用户行为topic
     */
    public static final String USER_FAVOR_TOPIC = "user-favor-topic";
    public static final String USER_COLLECT_TOPIC = "user-collect-topic";
    public static final String USER_LIKE_TOPIC = "user-like-topic";
    public static final String USER_CONTENT_VIEWED_TOPIC = "user-viewed-content-topic";

    /**
     * 内容模块topic
     */
    public static final String CONTENT_VIDEO_UPSERT_TOPIC = "content-video-upsert-topic";
    public static final String CONTENT_AUDIO_UPSERT_TOPIC = "content-audio-upsert-topic";
    public static final String CONTENT_ESSAY_UPSERT_TOPIC = "content-essay-upsert-topic";

    public static final String CONTENT_VIDEO_DELETE_TOPIC = "content-video-delete-topic";
    public static final String CONTENT_AUDIO_DELETE_TOPIC = "content-audio-delete-topic";
    public static final String CONTENT_ESSAY_DELETE_TOPIC = "content-essay-delete-topic";


}
