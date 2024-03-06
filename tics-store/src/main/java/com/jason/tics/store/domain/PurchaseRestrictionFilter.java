package com.jason.tics.store.domain;

import com.jason.tics.common.core.exception.TicsException;

import java.util.List;

/**
 * 购买限制
 * @author Jason
 */
public interface PurchaseRestrictionFilter {
    /**
     * 限制商品的id集合
     */
    List<Long> filterFor();

    /**
     * 某用户购买指定商品是否限制
     * 满足限制条件则抛出异常，购买操作打断，回滚事务
     */
    void filter(long uid, long item) throws TicsException;

    /**
     * 有关此购买限制的描述
     */
    String restrictionDescription();
}
