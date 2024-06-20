package com.example.demo.service.crawling;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CrawlingDTO;
import com.example.demo.mapper.CrawlingMapper;

/**
 * 설명: 크롤링 데이터 DB 저장 서비스
 * 작성자: 전영준
 * 최초생성: 2024-06-20
 * 수정일자: 
 */
@Service
public class CrawlingDBService {
    @Autowired
    CrawlingMapper mapper;

    @Autowired
    CrawlingDTO crawlingDTO;
    
    public int insertCrawlingData(List<CrawlingDTO> dtoList) {
        int resultCount = mapper.insertCrawlingData(dtoList);
        return resultCount;
    }

    public int selectTodayCrawlingDataCnt(Map<String, Object> map) {
        int resultCount = mapper.selectTodayCrawlingDataCnt(map);
        return resultCount;
    }

    public List<CrawlingDTO> selectTodayCrawlingData(Map<String, Object> map) {
        List<CrawlingDTO> result = mapper.selectTodayCrawlingData(map);
        return result;
    }



    // public int deleteCrawlingData(Map<String, Object> map) {
    //     int resultCount = mapper.deleteCrawlingData(map);
    //     return resultCount;
    // }
}
