package com.jason.tics.order.domain.pojo.vo;

import com.jason.tics.order.domain.OrderExpressInfo;
import com.jason.tics.order.domain.OrderItem;
import com.jason.tics.order.domain.OrderPayInfo;
import com.jason.tics.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jason
 */
@Data
@Builder
public class OrderVo {
    private Long orderId;

    private OrderExpressInfo orderExpressInfo;

    private OrderItem orderItem;

    private OrderPayInfo orderPayInfo;

    /**
     * 订单状态
     */
    private OrderStatus orderStatus;

    /**
     * 订单金额（这是还未进行优惠处理的价格）
     */
    private BigDecimal orderAmount;

    /**
     * 购买商品数量
     */
    private Integer itemNum;

    /**
     * 是否付款
     */
    private Boolean payed;
    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 完成时间
     */
    private Date finishTime;
    /**
     * 取消时间
     */
    private Date cancelTime;


    private Date createTime;
    private Date updateTime;


}
