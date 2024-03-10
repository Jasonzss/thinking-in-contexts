package com.jason.tics.order.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Jason
 */
@Data
@Entity
@Table
@Builder
public class OrderExpressInfo {
    @Id
    private Long orderExpressInfoId;
    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 省份、地区
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区、县
     */
    private String area;

    /**
     * 街道、镇
     */
    private String street;

    /**
     * 详细地址
     */
    private String address;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    public OrderExpressInfo() {

    }
}
