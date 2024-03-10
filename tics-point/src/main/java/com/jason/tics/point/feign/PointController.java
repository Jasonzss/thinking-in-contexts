package com.jason.tics.point.feign;

import cn.hutool.core.bean.BeanUtil;
import com.jason.tics.api.point.bo.PointBillBo;
import com.jason.tics.api.point.feign.PointFeignClient;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.point.repository.PointBillRepository;
import com.jason.tics.point.service.PointWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Jason
 */
@Component
public class PointController implements PointFeignClient {
    @Autowired
    private PointWalletService pointWalletService;
    @Autowired
    private PointBillRepository pointBillRepository;

    @Override
    public ServerResponseEntity<PointBillBo> createPointBill(long uid, BigDecimal billAmount, String billSource) {
        PointBillBo bo = BeanUtil
                .copyProperties(pointWalletService.createBill(uid, billAmount, billSource), PointBillBo.class);
        return ServerResponseEntity.success(bo);
    }

    @Override
    public ServerResponseEntity<PointBillBo> getBill(long billId) {
        PointBillBo bo = BeanUtil.copyProperties(pointBillRepository.getById(billId), PointBillBo.class);
        return ServerResponseEntity.success(bo);
    }
}
