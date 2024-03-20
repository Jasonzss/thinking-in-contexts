package com.jason.tics.user.domain;

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
@Entity
@Table
@Data
public class UserAddress {
    /**
     * 用户ID
     */
    private Long uid;

    @Id
    private Integer userAddressId;

    private String addressTag;

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
    private String provinceId;

    /**
     * 城市
     */
    private String cityId;

    /**
     * 区、县
     */
    private String areaId;

    /**
     * 街道、镇
     */
    private String streetId;

    /**
     * 详细地址
     */
    private String address;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
