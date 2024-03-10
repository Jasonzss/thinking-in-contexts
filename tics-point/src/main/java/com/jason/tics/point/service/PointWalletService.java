package com.jason.tics.point.service;

import com.jason.tics.point.domain.PointBill;

import java.math.BigDecimal;

/**
 * @author Jason
 */
public interface PointWalletService {
    /**
     * 创建账单
     */
    PointBill createBill(long uid, BigDecimal billAmount, String billSource);

    /**
     * 完成账单
     */
    PointBill payBill(long billId);
}
