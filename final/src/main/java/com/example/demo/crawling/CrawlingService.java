package com.example.demo.crawling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CrawlingDTO;
import com.example.demo.mapper.CrawlingMapper;

@Service
public class CrawlingService {
    @Autowired
    CrawlingMapper mapper;

    @Autowired
    CrawlingDTO memberDTO;
    
    public int insertCrawlingData(List<CrawlingDTO> dtoList) {
        return mapper.insertCrawlingData(dtoList);
    }
}
