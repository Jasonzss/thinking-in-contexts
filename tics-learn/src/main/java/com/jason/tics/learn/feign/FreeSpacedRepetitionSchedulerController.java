package com.jason.tics.learn.feign;

import com.jason.tics.api.learn.domain.WordLearningResult;
import com.jason.tics.api.learn.feign.FreeSpacedRepetitionSchedulerFeignClient;
import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.learn.service.FsrsService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * @author Jason
 */
@RestController
public class FreeSpacedRepetitionSchedulerController implements FreeSpacedRepetitionSchedulerFeignClient {
    @Autowired
    private FsrsService fsrsService;

    @Override
    public ServerResponseEntity<WordLearningResult> learnWordWithRating(@NotNull long uid, String word, @NotNull @Range(min = 0, max = 3) int rating) {
        return ServerResponseEntity.success(fsrsService.learnWordWithRating(uid, word, rating));
    }

    @Override
    public ServerResponseEntity<Set<WordLearningResult>> addWordSchedule(long uid, List<String> word) {
        return ServerResponseEntity.success(fsrsService.addWordSchedule(uid, word));
    }

    @Override
    public ServerResponseEntity<SortedSet<WordLearningResult>> listUserLearningWords(long uid) {
        return ServerResponseEntity.success(fsrsService.listUserWordLearningResult(uid));
    }
}
