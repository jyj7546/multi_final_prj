package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    CrawlingLogicService crawlingTest2;

    /**
     * 크롤링 호출 메소드
     * @param mart {emart, homeplus, lottemart}
     * @return
     */
    @GetMapping("{mart}")
    public ResponseEntity<String> crawl(@PathVariable("mart") String mart) {
        logger.info("IIIIIIIIII");
        logger.info(mart, "크롤링 시작");
        logger.info("IIIIIIIIII");

        boolean result = crawlingTest2.crawlEmart();

        if(result == true) {
            return ResponseEntity.ok("Crawling END SUCCESS");
        } else {
            return ResponseEntity.ok("Crawling END FAIL");
        }
        
    }
}
