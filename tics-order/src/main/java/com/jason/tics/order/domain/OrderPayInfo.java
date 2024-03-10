package com.jason.tics.order.domain;

import com.jason.tics.api.store.domain.PayType;
import lombok.Builder;
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
@Builder
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

    private Long billId;

    /**
     * 实付金额
     */
    private BigDecimal payment;

    private PayType payType;

    /**
     * 确认时间
     */
    private Date confirmTime;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    public OrderPayInfo() {
    }
}
