package com.jason.tics.point.feign;

import com.jason.tics.api.point.bo.PointBillBo;
import com.jason.tics.api.point.feign.PointFeignClient;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.point.repository.PointWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Jason
 */
@Component
public class PointController implements PointFeignClient {
    @Autowired
    private PointWalletRepository pointWalletRepository;

    @Override
    public ServerResponseEntity<PointBillBo> createPointBill(long uid, BigDecimal billAmount, String billSource) {
        return null;
    }

    @Override
    public ServerResponseEntity<PointBillBo> payBill(long billId) {
        return null;
    }
}
