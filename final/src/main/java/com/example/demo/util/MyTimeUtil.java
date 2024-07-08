package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import org.quartz.JobExecutionException;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 설명: 날짜관련 유틸성 클래스
 * 작성자: 전영준
 * 최초생성: 2024-06-20
 * 수정일자: 
 */
@Slf4j
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

    /**
     * 어제일자 구하기
     * @param format {"yyyyMMdd", "yyyy-MM-dd"}
     * @return
     */
    public static String getYesDate(String format) {
        String yes = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, -1);
        yes = sdf.format(calendar.getTime());

        return yes;
    }

    /**
     * 배치 일자 파라미터 형식(yyyyMMdd) 인지 체크하고 아닌 경우 처리
     * @param dateString
     * @return
     */
    public static String validBatchDate(String dateString) {
        // if(dateString == null || dateString.equals("") || dateString.length() == 0) {
        if(!StringUtils.hasText(dateString)) { // null이면 그냥 현재일자 넣어줌
            dateString = getNowDate("yyyyMMdd");
            log.error("배치잡 파라미터 날짜 없음. default(오늘일자) : {} 넣음", dateString);
        }

        boolean isValid = false;
        Pattern DATE_PATTERN = Pattern.compile("^\\d{8}$"); // 20240101

        if(dateString.length() >= 8) {
            dateString.replaceAll("[^\\d]", "");   // 문자 제거
            if(dateString.length() == 8) {
                isValid = DATE_PATTERN.matcher(dateString).matches();
            }
        }


        if(isValid == false) {
            log.error("배치잡 파라미터 날짜 형식(yyyyMMdd) 에러. 입력된 날짜 : {}", dateString);
            new JobExecutionException();    // 바로 쿼츠잡 익셉션 발생시켜서 처리하게 함
        } 

        return dateString;

    }

}
