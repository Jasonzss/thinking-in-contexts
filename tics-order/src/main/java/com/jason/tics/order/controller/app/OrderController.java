package com.jason.tics.order.controller.app;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.order.domain.Order;
import com.jason.tics.order.domain.pojo.dto.OrderDto;
import com.jason.tics.order.domain.pojo.vo.OrderVo;
import com.jason.tics.order.repository.OrderRepository;
import com.jason.tics.order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RestController("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @ApiOperation(value = "下单", response = Order.class)
    @GetMapping
    public ServerResponseEntity<OrderVo> order(OrderDto orderDto){
        return ServerResponseEntity.success(orderService.order(orderDto));
    }

    @ApiOperation(value = "获取订单信息", response = OrderVo.class)
    @GetMapping("/{id}")
    public ServerResponseEntity<OrderVo> get(@PathVariable long id){
        return ServerResponseEntity.success(orderService.getOrderDetail(id));
    }

    @GetMapping
    @ApiOperation(value = "查看用户个人订单", response = Page.class)
    public ServerResponseEntity<Page<Order>> list(@Uid long uid, Pageable pageable){
        return ServerResponseEntity.success(orderRepository.findAllByUid(uid, pageable));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改订单状态", response = OrderVo.class)
    public ServerResponseEntity<OrderVo> update(@PathVariable long id,
                                                @RequestParam
                                                @ApiParam(value = "只能是0-3，0代表取消订单，1代表订单确认支付，3代表订单确认完成")
                                                        int status){
        if (status == 0){
            return ServerResponseEntity.success(orderService.cancelOrder(id));
        }else if (status == 1) {
            return ServerResponseEntity.success(orderService.payed(id));
        }else if (status == 2) {
            return ServerResponseEntity.success(orderService.delivered(id));
        }else {
            throw new TicsException(ExceptionResponseEnum.BAD_URL_PARAM);
        }
    }
}
