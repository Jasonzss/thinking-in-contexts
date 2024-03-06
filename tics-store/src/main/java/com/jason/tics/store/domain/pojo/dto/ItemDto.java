package com.jason.tics.store.domain.pojo.dto;

import com.jason.tics.common.jpa.entity.Dto;
import com.jason.tics.store.domain.Item;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Nullable;

/**
 * @author Jason
 */
public class ItemDto implements Dto<Long, Item> {
    private Long itemId;

    private String itemName;

    /**
     * 售价
     */
    @Nullable
    private Long cost;

    /**
     * 积分售价
     * 两种售价为或的关系，只需取其中之一支付即可。属性为null代表无法以此方式支付
     */
    @Nullable
    private Long pointCost;

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
                .pointCost(pointCost)
                .details(details)
                .build();
    }

    @Override
    public Long getId() {
        return itemId;
    }
}
