package com.example.demo.crawling;

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
    String martCd;  // emart, homeplus, lottemart
    String subtitle;    // 실시간 인기 세일, 원쁠원 등.. 
    String goodsName;   // 각 마트 세일상품 브랜드명 + 상품명
    String price;   // 가격
    String link;    // 세일상품 직링크
}
