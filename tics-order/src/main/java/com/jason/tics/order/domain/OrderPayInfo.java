package com.jason.tics.order.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jason
 */

@Data
@Entity
@Table
public class OrderPayInfo {

    /**
     * 支付单号
     */
    @Id
    private Long payId;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 实付金额
     */
    private BigDecimal payment;

    /**
     * 确认时间
     */
    private Date confirmTime;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

}
