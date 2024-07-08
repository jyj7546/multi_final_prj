package com.example.demo.batch;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.example.demo.enumeration.MartCd;
import com.example.demo.service.crawling.CrawlingDBService;
import com.example.demo.service.crawling.CrawlingLogicService;
import com.example.demo.util.MyTimeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 크롤링 호출 잡
 * TODO: 나머지 마트 크롤링도 호출하게 추가
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmartCrawlingJob extends QuartzJobBean {
    // private final CrawlingDBService crawlingDBService;

    private final CrawlingLogicService crawlingLogicService;

    /**
     * 실제 수행할 서비스 호출
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
        
        try {
            // JobDataMap map = context.getJobDetail().getJobDataMap();
            // log.debug("RequestContractJob execute invoked, job-detail-key:{}, fired-time:{}, num:{}", 
            //     context.getJobDetail().getKey(), context.getFireTime(), map.getInt("num"));
            // log.debug("RequestContractJob execute complete");

            log.debug("{} 크롤링 배치잡 시작", MartCd.EMART.name());
            Map<String, Object> jobMap = context.getMergedJobDataMap(); // 쿼츠 파라미터 전달받음
            
            Map<String, Object> param = new HashMap<>();
            param.clear();
            param.put("martCd", MartCd.EMART.code()); 

            // 잡 파라미터 (배치요청일자) valid 체크
            if (jobMap != null && !jobMap.isEmpty() && jobMap.containsKey("date")) {
                param.put("date", jobMap.get("date"));
            } else {    // 파라미터 null일 경우 현재일자 넣기
                log.debug("배치요청일자 파라미터 null. default(어제일자) 넣음");
                param.put("date", MyTimeUtil.getYesDate("yyyyMMdd"));
            }
            
            // crawlingDBService.selectTodayCrawlingData(param);  
            crawlingLogicService.crawlingEmart(param);   // TODO: 마트별 분기 호출
        } catch (Exception e) {
            log.debug("배치잡 실행 중 오류 발생: {}", e.getMessage());
            e.printStackTrace();
            throw new JobExecutionException("배치잡 실행 중 오류 발생", e);
        }
        
        log.debug("JobDetail name ::: {}", context.getJobDetail().getKey().getName());
        log.debug("Job run time ::: {}", context.getFireTime());
        log.debug("이마트 크롤링 배치잡 정상종료");
        
        // throw new UnsupportedOperationException("Unimplemented method 'executeInternal'");
    }
    
}
