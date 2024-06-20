package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Component
@Data
/**
 * 각 마트 세일 정보 크롤링 데이터 저장을 위한 DTO
 */
public class CrawlingDTO {
    private String uinqueKey;
    private String martCd;  // emart, homeplus, lottemart
    private String subtitle;    // 각 마트 세일상품 구분
    private String goodsName;   // 각 마트 세일상품 브랜드명 + 상품명
    private int price;   // 가격
    private String goodsDirectLink;    // 세일상품 직링크
    private String crawlingDate;    // 크롤링일자
    private Date regDt; // 등록일자
	private Date updateDt; // 업데이트일자
}
