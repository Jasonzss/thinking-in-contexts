package com.jason.tics.api.point.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jason
 */
@Data
public class PointBillBo {
    private Long billId;
    /**
     * 账户的用户id
     */
    private Long uid;
    /**
     * 积分加减数量
     */
    private BigDecimal billAmount;
    /**
     * 流水来源
     */
    private String billSource;
    /**
     * 账单是否支付完成
     */
    private Boolean isPayed;

    private Date createTime;
    private Date updateTime;

}
