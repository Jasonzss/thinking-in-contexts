package com.jason.tics.point.domain;

import com.jason.tics.common.jpa.converter.BooleanConverter;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分账单
 * @author Jason
 */
@Data
@Entity
@Table
public class PointBill {
    /**
     * 流水单号
     */
    @Id
    private Long billId;

    /**
     * 账户的用户id
     */
    private Long uid;

    /**
     * 积分加减数量
     * 正数加积分
     * 负数扣积分
     */
    private BigDecimal billAmount;

    /**
     * 流水来源
     */
    private String billSource;

    /**
     * 账单是否支付完成
     */
    @Convert(converter = BooleanConverter.class)
    private Boolean payed;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

}
