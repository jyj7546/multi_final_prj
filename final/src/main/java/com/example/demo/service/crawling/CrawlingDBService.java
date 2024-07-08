package com.example.demo.service.crawling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CrawlingDTO;
import com.example.demo.enumeration.MartCd;
import com.example.demo.mapper.CrawlingMapper;
import com.example.demo.util.MyTimeUtil;

import lombok.RequiredArgsConstructor;

/**
 * 설명: 크롤링 데이터 DB 저장 서비스
 * 작성자: 전영준
 * 최초생성: 2024-06-20
 * 수정일자: 
 */
@Service
@RequiredArgsConstructor
public class CrawlingDBService {
    
    private final CrawlingMapper mapper;
    
    public int insertCrawlingData(List<CrawlingDTO> dtoList) {
        int resultCount = mapper.insertCrawlingData(dtoList);
        return resultCount;
    }

    public int getCrawlingDataCnt(Map<String, Object> map) {
        int resultCount = mapper.selectCrawlingDataCnt(map);
        return resultCount;
    }

    public List<CrawlingDTO> getCrawlingData(Map<String, Object> map) {
        Map<String, Object> param = new HashMap<>();
        param.clear();
        param.putAll(map);

        if(param.isEmpty()) {
            param.put("martCd", MartCd.EMART.code());
            param.put("date", MyTimeUtil.getNowDate("yyyyMMdd"));
        }
        
        List<CrawlingDTO> result = mapper.selectCrawlingData(param);
        return result;
    }



    // public int deleteCrawlingData(Map<String, Object> map) {
    //     int resultCount = mapper.deleteCrawlingData(map);
    //     return resultCount;
    // }
}
