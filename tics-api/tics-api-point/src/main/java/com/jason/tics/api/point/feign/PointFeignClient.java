package com.jason.tics.api.point.feign;

import com.jason.tics.api.point.bo.PointBillBo;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.math.BigDecimal;

/**
 * @author Jason
 */
@Component
@FeignClient(value = "tics-point")
public interface PointFeignClient {
    @PostMapping("/point/wallet/{uid}/bill")
    ServerResponseEntity<PointBillBo> createPointBill(long uid, BigDecimal billAmount, String billSource);

    @PutMapping("/point/wallet/{uid}/bill")
    ServerResponseEntity<PointBillBo> payBill(long billId);
}
