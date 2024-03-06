package com.jason.tics.order.controller.app;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.order.domain.Order;
import com.jason.tics.order.domain.pojo.dto.OrderDto;
import com.jason.tics.order.domain.pojo.vo.OrderVo;
import com.jason.tics.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ServerResponseEntity<Order> order(OrderDto orderDto){
        return ServerResponseEntity.success();
    }

    @GetMapping("/{id}")
    public ServerResponseEntity<OrderVo> get(@PathVariable long id){
        return ServerResponseEntity.success(orderService.getOrderDetail(id));
    }

    @GetMapping("/{id}")
    public ServerResponseEntity<Order> list(@Uid long uid){
        return ServerResponseEntity.success();
    }
}
