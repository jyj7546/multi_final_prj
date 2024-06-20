package com.example.demo.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 설명: 날짜관련 유틸성 클래스
 * 작성자: 전영준
 * 최초생성: 2024-06-20
 * 수정일자: 
 */
public final class MyTimeUtil {
    
    /**
     * 현재일자를 원하는 포맷으로 출력하는 유틸 메소드
     * @param format {"yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd",,,,}
     * @return
     */
    public static String getNowDate(String format) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return now.format(formatter);
    }

}
