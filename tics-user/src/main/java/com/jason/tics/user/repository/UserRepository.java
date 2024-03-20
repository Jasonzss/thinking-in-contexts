package com.jason.tics.user.repository;

import com.jason.tics.common.jpa.repository.TicsRepository;
import com.jason.tics.user.domain.User;

/**
 * @author Jason
 */
public interface UserRepository extends TicsRepository<User, Long> {
    User getByEmail(String email);
}
