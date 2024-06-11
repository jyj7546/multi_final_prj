package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TestDTO;
import com.example.demo.mapper.TestMapper;

@Service
public class testService {
    @Autowired
    private TestMapper mapper;

    @Autowired
    TestDTO testDTO;

    public String selectTest() {
        Map<String, Object> param = new HashMap<>();
        param.put("id","wdw429");
        TestDTO result = mapper.selectTest(param);
        return result.toString();
    }
}
