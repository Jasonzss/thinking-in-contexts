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
public class OrderItem {

    /**
     * 订单项ID
     */
    @Id
    private Long orderItemId;

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 购买商品个数
     */
    private Integer count;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 用户Id
     */
    private Long uid;

    /**
     * 默认商品第一张图
     */
    private String picUrl;



    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
