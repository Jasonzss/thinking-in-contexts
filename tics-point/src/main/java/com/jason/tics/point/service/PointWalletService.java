package com.jason.tics.point.service;

import com.jason.tics.point.domain.PointBill;
import com.jason.tics.point.domain.PointWallet;

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
    PointWallet payBill(long billId);
}
