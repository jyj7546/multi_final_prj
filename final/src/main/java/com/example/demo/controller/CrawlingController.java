package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.CrawlingDTO;
import com.example.demo.service.crawling.CrawlingDBService;
import com.example.demo.service.crawling.CrawlingLogicService;

/**
 * 설명: 크롤링 호출 컨트롤러
 * 작성자: 전영준
 * 최초생성: 2024-06-20
 * 수정일자: 
 */
@Controller
@RequestMapping("/api/crawl")
public class CrawlingController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    CrawlingLogicService crawlingLogicService;

    @Autowired
    CrawlingDBService crawlingDBService;

    /**
     * 크롤링 호출 메소드
     * @param mart {emart, homeplus, lottemart}
     * @return
     */
    @GetMapping("/{martCd}")
    public ResponseEntity<String> crawling(@PathVariable("martCd") String martCd) {
        // boolean result = crawlingLogicService.crawlingEmart();
        boolean result = crawlingLogicService.crawling(martCd);

        if(result == true) {
            return ResponseEntity.ok("Crawling END SUCCESS");
        } else {
            return ResponseEntity.ok("Crawling END FAIL");
        }
        
    }

    /**
     * 크롤링 데이터 출력 메소드 Model And View 로 변경 예정
     * @param martCd
     * @param today
     * @return
     */
    @GetMapping("/{martCd}/{date}")
    public ResponseEntity<String> getCrawlingData(@PathVariable("martCd") String martCd, @PathVariable("date") String date) {
        Map<String, Object> param = new HashMap<>();
        param.clear();
        param.put("martCd", martCd);
        param.put("crawlingDate", date);

        List<CrawlingDTO> result = crawlingDBService.selectTodayCrawlingData(param);
        logger.info("조회갯수 : {}", result.size());

        if(result.size() != 0) {    // TODO: null 체크 수정 필요
            
            // TODO: 화면 출력 서비스 호출

            return ResponseEntity.ok("Get Crawling END SUCCESS");
        } else {
            return ResponseEntity.ok("Get Crawling END FAIL");
        }
    }
}
