package com.jason.tics.order.service.impl;

import com.jason.tics.order.domain.pojo.vo.OrderVo;
import com.jason.tics.order.repository.OrderExpressInfoRepository;
import com.jason.tics.order.repository.OrderItemRepository;
import com.jason.tics.order.repository.OrderPayInfoRepository;
import com.jason.tics.order.repository.OrderRepository;
import com.jason.tics.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderExpressInfoRepository orderExpressInfoRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderPayInfoRepository orderPayInfoRepository;

    @Override
    public OrderVo getOrderDetail(long orderId) {
        return null;
    }

    @Override
    public OrderVo order() {
        return null;
    }

    @Override
    public OrderVo pay() {
        return null;
    }

    @Override
    public OrderVo expressOrder() {
        return null;
    }

    @Override
    public OrderVo finishOrder() {
        return null;
    }

    @Override
    public OrderVo cancelOrder() {
        return null;
    }
}
