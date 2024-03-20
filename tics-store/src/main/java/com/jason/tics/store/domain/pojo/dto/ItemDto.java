package com.jason.tics.store.domain.pojo.dto;

import com.jason.tics.common.jpa.entity.Dto;
import com.jason.tics.store.domain.Item;
import com.jason.tics.api.store.domain.PayType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * @author Jason
 */
@Data
public class ItemDto implements Dto<Long, Item> {
    private Long itemId;

    private String itemName;

    /**
     * 售价
     */
    private BigDecimal cost;

    /**
     * 积分售价
     * 两种售价为或的关系，只需取其中之一支付即可。属性为null代表无法以此方式支付
     */
    private PayType payType;

    /**
     * 商品详情
     */
    @Length(max = 2000)
    private String details;

    @Override
    public Item getSource() {
        return Item.builder()
                .itemName(itemName)
                .cost(cost)
                .payType(payType)
                .details(details)
                .build();
    }
}
