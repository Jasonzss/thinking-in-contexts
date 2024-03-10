package com.jason.tics.order.domain;

import com.jason.tics.api.store.domain.PayType;
import com.jason.tics.common.jpa.converter.BooleanConverter;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jason
 */

@Data
@Entity
@Table
@Builder
public class Order {
    @Id
    private Long orderId;

    /**
     * 下单用户的id
     */
    private Long uid;

    private Long orderExpressInfoId;

    private Long orderItemId;

    private Long orderPayInfoId;

    /**
     * 订单状态
     */
    @Enumerated
    private OrderStatus orderStatus;

    /**
     * 订单金额（这是还未进行优惠处理的价格）
     */
    private BigDecimal orderAmount;

    private PayType payType;

    /**
     * 购买商品数量
     */
    private Integer itemNum;

    /**
     * 是否付款
     */
    @Convert(converter = BooleanConverter.class)
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


    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    public Order() {

    }
}
