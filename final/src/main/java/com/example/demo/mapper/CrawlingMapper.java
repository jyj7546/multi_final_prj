package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.CrawlingDTO;

@Repository
@Mapper
public interface CrawlingMapper {
    int insertCrawlingData(List<CrawlingDTO> dtoList);
}
