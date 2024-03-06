package com.jason.tics.store.domain.restriction;

import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.store.domain.PurchaseRestrictionFilter;

import java.util.List;

/**
 * 单人周期购买限制
 * 例如：每个人每礼拜（7天）只能买一次
 * @author Jason
 */
public class PurchaseFrequencyRestrictionFilter implements PurchaseRestrictionFilter {
    /**
     * 每个周期的限制购买次数
     */
    private Integer times;

    /**
     * 限制周期
     */
    private Integer restrictionCycle;

    /**
     * 时间周期基本单位
     */
    private DateCycle dateCycleUnit;


    @Override
    public List<Long> filterFor() {
        return null;
    }

    @Override
    public void filter(long uid, long item) throws TicsException {

    }

    @Override
    public String restrictionDescription() {
        return null;
    }

    public enum DateCycle{
        DAY,
        WEEK,
        MONTH,
        YEAR;
    }
}
