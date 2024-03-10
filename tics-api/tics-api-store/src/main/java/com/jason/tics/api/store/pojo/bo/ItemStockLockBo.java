package com.jason.tics.api.store.pojo.bo;

import java.util.Date;

/**
 * @author Jason
 */
public class ItemStockLockBo {
    private Long itemStockLockId;
    /**
     * 商品id
     */
    private Long itemId;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 锁定库存数量
     */
    private Integer count;

    private Date createTime;
    private Date updateTime;
}
