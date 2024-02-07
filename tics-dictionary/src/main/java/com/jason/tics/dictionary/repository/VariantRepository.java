package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.extension.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 */
public interface VariantRepository extends JpaRepository<Variant, String> {
    List<Variant> findAllByVariantsGroupId(String groupId);
}
