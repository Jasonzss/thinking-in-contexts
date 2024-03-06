package com.jason.tics.api.store.pojo.bo;

import java.util.Date;

/**
 * @author Jason
 */
public class ItemStockBo {
    private Long itemId;

    /**
     * 锁定库存
     */
    private Integer lockStock;

    /**
     * 可售卖库存
     */
    private Integer stock;

    private Date createTime;

    private Date updateTime;
}
