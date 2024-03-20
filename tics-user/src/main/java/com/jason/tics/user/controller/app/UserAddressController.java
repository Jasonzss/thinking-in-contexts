package com.jason.tics.user.controller.app;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.user.domain.UserAddress;
import com.jason.tics.user.domain.dto.UserAddressDto;
import com.jason.tics.user.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RestController("/user")
public class UserAddressController {
    @Autowired
    private UserAddressRepository userAddressRepository;

    @GetMapping("/{uid}/address/{id}")
    public ServerResponseEntity<UserAddress> getAddress(@PathVariable long uid, @PathVariable long id){
        return ServerResponseEntity.success(userAddressRepository.getByUidAndUserAddressId(uid, id));
    }

    @PostMapping
    public ServerResponseEntity<UserAddress> addAddress(@RequestBody @Validated UserAddressDto dto){
        return ServerResponseEntity.success(userAddressRepository.addByPojo(dto));
    }

    @DeleteMapping("/{uid}/address/{id}")
    public ServerResponseEntity<UserAddress> deleteAddress(@PathVariable long id){
        userAddressRepository.deleteById(id);
        return ServerResponseEntity.success();
    }

    @PutMapping("/{uid}/address/{id}")
    public ServerResponseEntity<UserAddress> updateAddress(@PathVariable long id, @RequestBody @Validated UserAddressDto dto){
        return ServerResponseEntity.success(userAddressRepository.updateByPojo(id, dto));
    }
}
