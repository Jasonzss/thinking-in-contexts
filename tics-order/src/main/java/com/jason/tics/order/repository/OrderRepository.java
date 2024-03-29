package com.jason.tics.order.repository;

import com.jason.tics.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByUid(long uid, Pageable pageable);
}
