package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.extension.Similarity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jason
 */
@SpringBootTest
@Transactional
@Slf4j
public class SimilarityRepositoryTest {
    @Autowired
    private SimilarityRepository similarityRepository;

    @Test
    public void testSave(){
        Similarity similarity = new Similarity();
        similarity.setOne("price");
        similarity.setTwo("prize");
        log.info("新增相似:"+similarityRepository.save(similarity).toString());
    }
}
