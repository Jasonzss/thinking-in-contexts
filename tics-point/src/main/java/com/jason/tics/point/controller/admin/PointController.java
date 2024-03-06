package com.jason.tics.point.controller.admin;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.point.domain.PointBill;
import com.jason.tics.point.domain.PointWallet;
import com.jason.tics.point.repository.PointBillRepository;
import com.jason.tics.point.repository.PointWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController("/admin/point")
public class PointController {
    @Autowired
    private PointWalletRepository pointWalletRepository;
    @Autowired
    private PointBillRepository pointBillRepository;

    @GetMapping("/wallet")
    public ServerResponseEntity<Page<PointWallet>> list(Pageable pageable){
        return ServerResponseEntity.success(pointWalletRepository.findAll(pageable));
    }

    @GetMapping("/wallet/bill/{id}")
    public ServerResponseEntity<PointBill> getBill(@PathVariable long id){
        return ServerResponseEntity.success(pointBillRepository.getById(id));
    }

    @GetMapping("/wallet/bill")
    public ServerResponseEntity<Page<PointBill>> listBill(Pageable pageable){
        return ServerResponseEntity.success(pointBillRepository.findAll(pageable));
    }
}
