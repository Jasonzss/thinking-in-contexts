package com.jason.tics.translation.repository;

import com.jason.tics.translation.domain.youdao.YouDaoTextTranslationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface YoudaoRepository extends JpaRepository<YouDaoTextTranslationResponse, String> {

}
