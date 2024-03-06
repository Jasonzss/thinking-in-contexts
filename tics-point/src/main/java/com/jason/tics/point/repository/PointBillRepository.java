package com.jason.tics.point.repository;

import com.jason.tics.point.domain.PointBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface PointBillRepository extends JpaRepository<PointBill, Long> {
    Page<PointBill> findAllByUid(long uid, Pageable pageable);
}
