package com.jason.tics.store.domain;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品库存
 * @author Jason
 */
@Data
@Entity
@Table
@Builder
public class ItemStock {
    /**
     * 商品id
     */
    @Id
    private Long itemId;

    /**
     * 锁定库存
     */
    private Integer lockStock;

    /**
     * 可售卖库存
     */
    private Integer availableStock;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    public ItemStock() {
    }

    public void lockStock(int num){
        if(availableStock < num){
            throw new TicsException(ExceptionResponseEnum.SHOW_FAIL.setMsg("可用库存不足，稍后再试或看看别的吧"));
        }

        availableStock -= num;
        lockStock += num;
    }

    public void releaseLock(int num){
        availableStock += num;
        lockStock -= num;
    }

    public void confirmStockLock(int num){
        lockStock -= num;
    }
}
