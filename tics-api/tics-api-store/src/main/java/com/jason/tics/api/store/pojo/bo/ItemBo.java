package com.jason.tics.api.store.pojo.bo;

import com.jason.tics.api.store.domain.PayType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author Jason
 */
@Data
public class ItemBo {
    private Long itemId;

    /**
     * 键是图片index
     * 值是图片url
     */
    private Map<Integer, String> itemImagesUrl;

    private String itemName;

    /**
     * 售价
     */
    private BigDecimal cost;

    private PayType payType;

    /**
     * 商品详情
     */
    private String details;

    private Date createTime;
    private Date updateTime;
}
