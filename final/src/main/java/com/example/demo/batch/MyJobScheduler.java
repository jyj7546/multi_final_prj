package com.example.demo.batch;

import java.util.HashMap;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Configuration;

import com.example.demo.util.MyTimeUtil;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

/**
 * 배치잡 스케줄 등록 클래스
 */
@Configuration
@RequiredArgsConstructor
public class MyJobScheduler {
    
    private final Scheduler scheduler;

    /**
     * 등록할 잡 선언 및 쿼츠 스케줄러 잡 등록 클래스에 파라미터로 넘김
     * TODO: 스케줄링 컨트롤러로 해당 메소드 호출하여 UI로 스케줄링 관리(현재는 PostContruct 어노테이션으로 쿼츠 스케줄러 스프링부트로 작동시 자동호출되는 중)
     * @throws SchedulerException
     */
    @PostConstruct
    public void scheduleJobs() throws SchedulerException {

        // TODO: 외부(스케줄링컨트롤러)에서 입력받기
        Map<String, Object> param = new HashMap<>();   
        param.clear();
        param.put("date", MyTimeUtil.getYesDate("yyyyMMdd"));

        // MyTimeUtil.validBatchDate(null); // TODO: 배치 일자 파라미터 valid 체크

        // 잡 등록
        scheduleJob(EmartCrawlingJob.class, "emartJob", "emartGroup", "0 0/10 * * * ?", param);   // TODO: 테스트를 위해 10분마다 반복 수행
        // TODO: 다른 잡클래스 생성되면 여기에다 계속 추가
        // ...
    }

    /**
     * 쿼츠 스케줄러 잡 등록 클래스
     * @param jobClass
     * @param jobName
     * @param groupName
     * @param cronExpression
     * @param param
     * @throws SchedulerException
     */
    public void scheduleJob(Class<? extends Job> jobClass, String jobName, String groupName, String cronExpression, Map<String, Object> param) throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(param);

        JobDetail jobDetail = JobBuilder.newJob(jobClass)
            .withIdentity(jobName, groupName)
            .usingJobData(jobDataMap)
            .build();

        Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity(jobName + "Trigger", groupName)
            .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
            .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

}
