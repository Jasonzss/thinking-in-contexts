package com.jason.tics.order.domain.pojo.dto;

/**
 * @author Jason
 */
public class OrderDto {
    //订单地址信息
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


    //订单商品信息
    /**
     * 商品ID
     */
    private Long itemId;
    /**
     * 购买商品个数
     */
    private Integer count;
}
