package com.example.demo.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.TestDTO;

@Repository
@Mapper
public interface testMapper {
    TestDTO selectTest(Map<String, Object> map);
}
