package com.jason.tics.exercise.domain.exam;

import com.jason.tics.common.cache.constant.ExerciseCacheNames;
import com.jason.tics.common.cache.service.RedisCacheService;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.common.jpa.utils.JpaCrudUtil;
import com.jason.tics.exercise.repository.ScantronRepository;
import com.jason.tics.exercise.repository.PaperAnswerRepository;
import com.jason.tics.exercise.repository.PaperRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 模拟考试系统，只进行模拟考试，可以随时暂停或继续
 * @author Jason
 */
@Service
@Slf4j
public class PreparationExamSystem {
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private ScantronRepository scantronRepository;
    @Autowired
    private PaperAnswerRepository paperAnswerRepository;
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //考试功能

    public Scantron startExam(long paperId, long uid){
        //先检查此试卷是否存在未完成的
        if (findUnfinishedPaper(paperId, uid) != null) {
            throw new TicsException(ExceptionResponseEnum.SHOW_FAIL.setMsg("此试卷有尚未完成的考试，请先继续或关闭先前的考试"));
        }

        Paper paper = paperRepository.getById(paperId);
        Scantron scantron = new Scantron();
        scantron.setPaperId(paper.getId());
        scantron.setUid(uid);
        scantron.setScore(0.0);
        scantron.setTimeSpent(0L);
        scantron.setIsFinished(false);
        Scantron exam = scantronRepository.save(scantron);

        //将考试结束任务放入定时任务系统
        ExaminationInfo info = new ExaminationInfo();
        info.setPaperId(paper.getId());
        info.setUid(uid);
        info.setScantronId(exam.getId());
        info.setContinueTime(System.currentTimeMillis());
        info.setTimeLimit(paper.getTimeLimit());
        info.setUsedTime(0L);

        String examId = getExamFinishId(scantron.getId());
        redisCacheService.set(examId, scantron.getId(), paper.getTimeLimit());
        redisCacheService.set(getExamInfoId(scantron.getId()), info);

        return exam;
    }

    public Scantron findUnfinishedPaper(long paperId, long uid){
        return scantronRepository.findByUidAndIsFinishedAndPaperId(uid, false, paperId).orElse(null);
    }

    public void pauseExam(long scantronId){
        //暂停考试，将定时任务系统中的试卷取出并统计信息
        ExaminationInfo info = redisCacheService.getAndDel(getExamInfoId(scantronId));
        redisCacheService.cancelExpiration(getExamFinishId(scantronId));
        Scantron scantron = scantronRepository.getById(scantronId);

        //更新考试信息
        scantron.setTimeSpent(scantron.getTimeSpent()+(System.currentTimeMillis()-info.getContinueTime()));

        //持久化考试信息
        scantronRepository.save(scantron);
    }

    public Scantron continueExam(long scantronId){
        //查询是否存在该考试
        Optional<Scantron> byId = scantronRepository.findById(scantronId);
        Scantron scantron =
                byId.orElseThrow(new TicsException(ExceptionResponseEnum.RESOURCE_NOT_FOUND));

        //该考试是否完成
        if (scantron.getIsFinished()) {
            throw new TicsException(ExceptionResponseEnum.SHOW_FAIL.setMsg("考试已交卷，无法再作答"));
        }

        //继续考试
        //TODO 试卷信息被删了怎么办？此处并没有给出业务上的处理方式，service是否有必要加在这个Repository上
        Paper paper = paperRepository.getById(scantron.getPaperId());
        String examId = getExamFinishId(scantron.getId());

        //将考试结束任务放入定时任务系统
        ExaminationInfo info = new ExaminationInfo();
        info.setPaperId(paper.getId());
        info.setUid(scantron.getUid());
        info.setScantronId(scantronId);
        info.setContinueTime(System.currentTimeMillis());
        info.setTimeLimit(paper.getTimeLimit());
        info.setUsedTime(scantron.getTimeSpent());

        redisCacheService.set(examId, scantron.getId(), paper.getTimeLimit()- scantron.getTimeSpent());
        redisCacheService.set(getExamInfoId(scantron.getId()), info);

        return scantron;
    }

    /**
     * 时间截止，自动交卷
     */
    @EventListener
    public void autoSubmit(RedisKeyExpiredEvent<?> event){
        //获取结束考试的信息
        String examId = (String) redisTemplate.getKeySerializer().deserialize(event.getId());
        log.info("考试结束["+ examId +"]");
        assert examId != null;
        long id = Long.parseLong(examId.replace(ExerciseCacheNames.EXAM_FINISH_PREFIX, ""));

        ExaminationInfo info = redisCacheService.getAndDel(getExamInfoId(id));

        //持久化考试信息
        Scantron scantron = scantronRepository.getById(id);
        scantron.setIsFinished(true);
        scantron.setTimeSpent(info.getTimeLimit());
        scantronRepository.save(scantron);
    }

    /**
     * 手动交卷
     */
    public Scantron finishExam(long scantronId){
        ExaminationInfo info = redisCacheService.getAndDel(getExamInfoId(scantronId));
        redisCacheService.del(getExamFinishId(scantronId));

        //保存试卷信息
        Scantron scantron = scantronRepository.getById(scantronId);
        scantron.setTimeSpent(scantron.getTimeSpent()+(System.currentTimeMillis()-info.getContinueTime()));
        scantron.setIsFinished(true);

        return scantronRepository.save(scantron);
    }

    public PaperAnswer saveAnswer(PaperAnswer answer){
        Optional<PaperAnswer> paperAnswer = paperAnswerRepository
                .findByScantronIdAndIdx(answer.getScantron().getId(), answer.getIdx());
        return paperAnswer.map(value ->
                JpaCrudUtil.updateResource(answer, value.getId(), paperAnswerRepository))
                .orElseGet(() -> paperAnswerRepository.save(answer));
    }

    //后台试卷管理

    public Paper savePaper(Paper paper){
        return paperRepository.save(paper);
    }

    public void deletePaper(long paperId){
        paperRepository.deleteById(paperId);
    }

    public Paper getPaper(long paperId){
        return paperRepository.getById(paperId);
    }

    public Page<Paper> listPaperPage(Pageable pageable){
        return paperRepository.findAll(pageable);
    }

    private String getExamFinishId(long scantronId){
        return ExerciseCacheNames.EXAM_FINISH_PREFIX +scantronId;
    }

    private String getExamInfoId(long scantronId){
        return ExerciseCacheNames.EXAM_INFO_PREFIX+scantronId;
    }
}
