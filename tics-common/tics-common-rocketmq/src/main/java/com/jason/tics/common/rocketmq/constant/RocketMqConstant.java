package com.jason.tics.common.rocketmq.constant;

/**
 * @author Jason
 */
public class RocketMqConstant {

    // 延迟消息等级： 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h (1-18)

    /**
     * 未支付订单取消时间的等级
     * 16级，即30分钟
     */
    public static final int ORDER_CANCEL_TIME_LEVEL = 16;

    /**
     * 默认的发送消息超时时间
     */
    public static long TIMEOUT = 3000;

    /**
     * 测试topic
     */
    public static final String TEST_TOPIC = "test-topic";


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

    /**
     * 订单模块topic
     */
    public static final String PLACE_AN_ORDER_TOPIC = "place-an-order-topic";
    public static final String ORDER_TIME_OUT_TOPIC = "order-time-out-topic";
}
