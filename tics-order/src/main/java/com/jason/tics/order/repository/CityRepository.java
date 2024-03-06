package com.jason.tics.order.repository;

import com.jason.tics.order.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface CityRepository extends JpaRepository<City, String> {
    List<City> findAllByProvinceCode(String provinceCode);
}
