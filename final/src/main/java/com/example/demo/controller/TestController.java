package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.testService;


@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    testService service;

    @GetMapping("test1")
    public String getMethodName(Model model) {

        logger.debug("로그 디버그 테스트 ㄱ");
        logger.info("로그 인포 테스트 ㄱ");
        logger.error("로그 에러 테스트 ㄱ");
        logger.warn("로그 워닝 테스트 ㄱ");

        // String result = service.selectTest();
        // model.addAttribute("result", result);
        
        return "mypage-main";
    }


    
}
