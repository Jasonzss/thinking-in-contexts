package com.jason.tics.order.service;

import com.jason.tics.order.domain.pojo.vo.OrderVo;

/**
 * @author Jason
 */
public interface OrderService {
    /**
     * 获取订单详细信息
     */
    OrderVo getOrderDetail(long orderId);

    /**
     * 下单
     */
    OrderVo order();

    /**
     * 支付订单
     */
    OrderVo pay();

    OrderVo expressOrder();

    OrderVo finishOrder();

    OrderVo cancelOrder();
}
