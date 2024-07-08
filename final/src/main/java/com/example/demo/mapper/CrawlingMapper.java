package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.CrawlingDTO;

@Mapper
public interface CrawlingMapper {
    int insertCrawlingData(List<CrawlingDTO> dtoList);
    int selectCrawlingDataCnt(Map<String, Object> map);
    List<CrawlingDTO> selectCrawlingData(Map<String, Object> map);

    // int deleteCrawlingData(Map<String, Object> map);
}
