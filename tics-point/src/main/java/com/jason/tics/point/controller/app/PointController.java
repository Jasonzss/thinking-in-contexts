package com.jason.tics.point.controller.app;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.common.security.annotation.Uid;
import com.jason.tics.point.domain.PointBill;
import com.jason.tics.point.domain.PointWallet;
import com.jason.tics.point.repository.PointBillRepository;
import com.jason.tics.point.repository.PointWalletRepository;
import com.jason.tics.point.service.PointWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController("/point")
public class PointController {
    @Autowired
    private PointWalletRepository pointWalletRepository;
    @Autowired
    private PointBillRepository pointBillRepository;
    @Autowired
    private PointWalletService pointWalletService;

    @GetMapping("/wallet")
    public ServerResponseEntity<PointWallet> get(@Uid long uid){
        return ServerResponseEntity.success(pointWalletRepository.getById(uid));
    }

    @GetMapping("/wallet/{uid}/bill/{id}")
    public ServerResponseEntity<PointBill> getBill(@PathVariable long id){
        return ServerResponseEntity.success(pointBillRepository.getById(id));
    }

    @GetMapping("/wallet/{uid}/bill")
    public ServerResponseEntity<Page<PointBill>> listBill(@PathVariable long uid, Pageable pageable){
        return ServerResponseEntity.success(pointBillRepository.findAllByUid(uid, pageable));
    }

    @PutMapping("/wallet/{uid}/bill")
    ServerResponseEntity<PointBill> payBill(long billId){
        return ServerResponseEntity.success(pointWalletService.payBill(billId));
    }
}
