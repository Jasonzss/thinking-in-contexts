package com.jason.tics.order.repository;

import com.jason.tics.order.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface AreaRepository extends JpaRepository<Area, String> {
    List<Area> findAllByProvinceCodeAndCityCode(String provinceCode, String cityCode);
}
