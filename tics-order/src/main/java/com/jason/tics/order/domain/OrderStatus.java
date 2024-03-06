package com.jason.tics.order.domain;

/**
 * @author Jason
 */
public enum OrderStatus {
    UNPAID("待支付"),
    UNDELIVERED("待发货"),
    UNRECEIVED("待收货"),
    CANCEL("买家已取消"),
    TIME_OUT("订单超时取消"),
    FINISH("已完成");

    OrderStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
