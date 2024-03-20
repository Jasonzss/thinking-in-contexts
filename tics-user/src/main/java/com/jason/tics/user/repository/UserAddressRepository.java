package com.jason.tics.user.repository;

import com.jason.tics.common.jpa.repository.TicsRepository;
import com.jason.tics.user.domain.UserAddress;

/**
 * @author Jason
 */
public interface UserAddressRepository extends TicsRepository<UserAddress, Long> {
    UserAddress getByUidAndUserAddressId(long uid, long userAddressId);
}
