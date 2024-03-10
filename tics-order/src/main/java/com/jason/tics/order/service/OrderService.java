package com.jason.tics.order.service;

import com.jason.tics.order.domain.pojo.dto.OrderDto;
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
    OrderVo order(OrderDto orderDto);

    /**
     * 订单已支付
     */
    OrderVo payed(long orderId);

    /**
     * 订单已发出
     */
    OrderVo delivered(long orderId);

    /**
     * 订单已完成
     */
    OrderVo finish(long orderId);

    /**
     * 订单取消
     */
    OrderVo cancelOrder(long orderId);

    /**
     * 订单超时取消
     */
    void timeout(long orderId);
}
