package com.jason.tics.point.service.impl;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.point.domain.PointBill;
import com.jason.tics.point.domain.PointWallet;
import com.jason.tics.point.repository.PointBillRepository;
import com.jason.tics.point.repository.PointWalletRepository;
import com.jason.tics.point.service.PointWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Jason
 */
@Service
public class PointWalletServiceImpl implements PointWalletService {
    @Autowired
    private PointWalletRepository pointWalletRepository;
    @Autowired
    private PointBillRepository pointBillRepository;

    @Override
    public PointBill createBill(long uid, BigDecimal billAmount, String billSource) {
        PointBill bill = new PointBill();
        bill.setUid(uid);
        bill.setBillAmount(billAmount);
        bill.setBillSource(billSource);

        bill.setPayed(false);
        return null;
    }

    @Override
    public PointBill payBill(long billId) {
        PointBill bill = pointBillRepository.getById(billId);
        PointWallet wallet = pointWalletRepository.getById(bill.getUid());
        //判断是收款还是付款
        if (bill.getBillAmount().compareTo(BigDecimal.ZERO) < 0){
            //判断是否够付款
            if (wallet.getBalance().compareTo(bill.getBillAmount()) < 0) {
                throw new TicsException(ExceptionResponseEnum.INSUFFICIENT_POINT);
            }
        }

        //改动积分数量
        wallet.setBalance(wallet.getBalance().add(bill.getBillAmount()));
        pointWalletRepository.save(wallet);

        //完成账单
        bill.setPayed(true);
        return pointBillRepository.save(bill);
    }
}
