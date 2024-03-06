package com.jason.tics.order.domain;

/**
 * @author Jason
 */
public enum DeliveryType {
    /**
     * 在线产品，无需快递
     */
    ONLINE,
    /**
     * 快递服务，但物流问题目前还解决不了
     */
    EXPRESS
}
