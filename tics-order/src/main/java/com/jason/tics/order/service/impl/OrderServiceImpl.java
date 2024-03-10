package com.jason.tics.order.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.jason.tics.api.point.feign.PointFeignClient;
import com.jason.tics.api.store.domain.PayType;
import com.jason.tics.api.store.feign.ItemFeignClient;
import com.jason.tics.api.store.feign.ItemStockFeignClient;
import com.jason.tics.api.store.pojo.bo.ItemBo;
import com.jason.tics.common.cache.constant.BusinessIdKey;
import com.jason.tics.common.cache.service.IdService;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.common.rocketmq.annotation.SendMessage;
import com.jason.tics.common.rocketmq.constant.RocketMqConstant;
import com.jason.tics.order.component.callback.OrderTimeoutSendCallback;
import com.jason.tics.order.domain.*;
import com.jason.tics.order.domain.pojo.dto.OrderDto;
import com.jason.tics.order.domain.pojo.vo.OrderVo;
import com.jason.tics.order.repository.OrderExpressInfoRepository;
import com.jason.tics.order.repository.OrderItemRepository;
import com.jason.tics.order.repository.OrderPayInfoRepository;
import com.jason.tics.order.repository.OrderRepository;
import com.jason.tics.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Jason
 */
@Service
public class OrderServiceImpl implements OrderService {
    public static final String ORDER_PAYMENT_SOURCE = "官方订单服务";

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderExpressInfoRepository orderExpressInfoRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderPayInfoRepository orderPayInfoRepository;
    @Autowired
    private ItemStockFeignClient itemStockFeignClient;
    @Autowired
    private ItemFeignClient itemFeignClient;
    @Autowired
    private IdService idService;
    @Autowired
    private PointFeignClient pointFeignClient;

    @Override
    public OrderVo getOrderDetail(long orderId) {
        Order order = orderRepository.getById(orderId);
        OrderExpressInfo expressInfo = orderExpressInfoRepository.getById(order.getOrderExpressInfoId());
        OrderItem item = orderItemRepository.getById(order.getOrderItemId());
        OrderPayInfo payInfo = orderPayInfoRepository.getById(order.getOrderPayInfoId());
        return getOrderVo(order, expressInfo, item, payInfo);
    }

    @SendMessage(topic = RocketMqConstant.ORDER_TIME_OUT_TOPIC,
            delayLevel = RocketMqConstant.ORDER_CANCEL_TIME_LEVEL,
            sendCallbackFactory = OrderTimeoutSendCallback.class)
    @Override
    public OrderVo order(OrderDto orderDto) {
        Long orderId = idService.getIncreaseId(BusinessIdKey.ORDER_KEY);
        //尝试锁住库存
        itemStockFeignClient.lockItems(orderDto.getItemId(), orderDto.getCount(), orderId);

        OrderExpressInfo expressInfo = OrderExpressInfo.builder()
                .uid(orderDto.getUid())
                .consignee(orderDto.getConsignee())
                .phoneNumber(orderDto.getPhoneNumber())
                .province(orderDto.getProvince())
                .city(orderDto.getCity())
                .area(orderDto.getArea())
                .street(orderDto.getStreet())
                .address(orderDto.getAddress())
                .build();
        OrderExpressInfo orderExpressInfo = orderExpressInfoRepository.save(expressInfo);

        ItemBo itemBo = itemFeignClient.getItem(orderDto.getItemId()).getData();
        OrderItem orderItem = OrderItem.builder()
                .itemId(orderDto.getItemId())
                .count(orderDto.getCount())
                .price(itemBo.getCost())
                .payType(itemBo.getPayType())
                .itemName(itemBo.getItemName())
                .uid(orderDto.getUid())
                .picUrl(itemBo.getItemImagesUrl().get(0))
                .build();
        orderItem = orderItemRepository.save(orderItem);

        //TODO 优惠券等金额计算
        BigDecimal actuallyPayment = orderItem.getPrice().multiply(new BigDecimal(orderItem.getCount()));

        //期望的支付信息，实际支付信息在各自的支付服务中
        OrderPayInfo payInfo = OrderPayInfo.builder()
                .uid(orderDto.getUid())
                .payType(itemBo.getPayType())
                .payment(actuallyPayment)
                .build();
        Long billId;
        if (itemBo.getPayType().equals(PayType.POINT)){
            //创建积分支付账单
            billId = pointFeignClient
                    .createPointBill(payInfo.getUid(), payInfo.getPayment(), ORDER_PAYMENT_SOURCE).getData().getBillId();
        }else {
            //TODO 创建现金支付账单
            billId = 0L;
        }
        payInfo.setBillId(billId);
        payInfo = orderPayInfoRepository.save(payInfo);

        Order order = Order.builder()
                .orderId(orderId)
                .uid(orderDto.getUid())
                .orderExpressInfoId(orderExpressInfo.getOrderExpressInfoId())
                .orderItemId(orderItem.getOrderItemId())
                .orderPayInfoId(payInfo.getPayId())
                .orderStatus(OrderStatus.UNPAID)
                .orderAmount(orderItem.getPrice().multiply(new BigDecimal(orderItem.getCount())))
                .payType(orderItem.getPayType())
                .itemNum(orderItem.getCount())
                .payed(false)
                .build();
        order = orderRepository.save(order);

        return getOrderVo(order, expressInfo, orderItem, payInfo);
    }

    private OrderVo getOrderVo(Order order, OrderExpressInfo expressInfo, OrderItem orderItem, OrderPayInfo payInfo){
        return OrderVo.builder()
                .orderId(order.getOrderId())
                .orderExpressInfo(expressInfo)
                .orderItem(orderItem)
                .orderPayInfo(payInfo)
                .orderStatus(order.getOrderStatus())
                .orderAmount(order.getOrderAmount())
                .itemNum(order.getItemNum())
                .payed(order.getPayed())
                .payTime(order.getPayTime())
                .deliveryTime(order.getDeliveryTime())
                .finishTime(order.getFinishTime())
                .cancelTime(order.getCancelTime())
                .createTime(order.getCreateTime())
                .updateTime(order.getUpdateTime())
                .build();
    }

    @Override
    public OrderVo payed(long orderId) {
        Order order = orderRepository.getById(orderId);
        if (order.getPayType().equals(PayType.POINT)) {
            //积分支付
            OrderPayInfo payInfo = orderPayInfoRepository.getById(order.getOrderPayInfoId());
            if (pointFeignClient.getBill(payInfo.getBillId()).getData().getPayed()) {
                //确定已支付，更新确认时间
                DateTime date = DateUtil.date();
                payInfo.setConfirmTime(date);
                order.setPayTime(date);
                orderPayInfoRepository.save(payInfo);
            }
        }

        //更改可用库存
        itemStockFeignClient.deleteStockLock(orderId, true);
        //修改订单状态
        order.setOrderStatus(OrderStatus.UNDELIVERED);
        orderRepository.save(order);
        //TODO 现金支付

        return getOrderDetail(orderId);
    }

    @Override
    public OrderVo delivered(long orderId) {
        //TODO 物流业务&虚拟产品发送 待开发
        Order order = orderRepository.getById(orderId);
        order.setDeliveryTime(DateUtil.date());
        order.setOrderStatus(OrderStatus.UNRECEIVED);
        orderRepository.save(order);

        return getOrderDetail(orderId);
    }

    @Override
    public OrderVo finish(long orderId) {
        Order order = orderRepository.getById(orderId);
        order.setFinishTime(DateUtil.date());
        order.setOrderStatus(OrderStatus.FINISH);
        orderRepository.save(order);
        return getOrderDetail(orderId);
    }

    @Override
    public OrderVo cancelOrder(long orderId) {
        return doCancelOrder(orderId, OrderStatus.CANCEL);
    }

    @Override
    public void timeout(long orderId) {
        doCancelOrder(orderId, OrderStatus.TIME_OUT);
    }

    private OrderVo doCancelOrder(long orderId, OrderStatus orderStatus){
        Order order = orderRepository.getById(orderId);
        if (order.getPayed()) {
            //TODO 退款服务
            throw new TicsException(ExceptionResponseEnum.SHOW_FAIL.setMsg("支付后无法取消订单，请联系工作人员"));
        }
        order.setCancelTime(DateUtil.date());
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        itemStockFeignClient.deleteStockLock(orderId, false);
        return getOrderDetail(orderId);
    }
}
