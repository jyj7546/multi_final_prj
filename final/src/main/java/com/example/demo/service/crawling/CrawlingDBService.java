package com.example.demo.service.crawling;

import java.util.List;

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
    CrawlingDTO memberDTO;
    
    public int insertCrawlingData(List<CrawlingDTO> dtoList) {
        return mapper.insertCrawlingData(dtoList);  // TODO: 적재 성공 시, 리턴 값(적재 성공 갯수인지) 확인 하고 이후단 분기 처리 필요
    }
}
