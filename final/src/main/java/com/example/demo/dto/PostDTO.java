package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

// @Data
@ToString
@Component
@Data
public class PostDTO {
    private String uniqueKey;   // 유니크키
    private String postId; // 게시글 ID
    private int seq; // 순번
    private String memId; // 회원 ID
    private int likeCnt; // 좋아요수
    private String imgSrc; // 이미지 경로
    private String contents; // 내용
    private String hashtag; // 해쉬태그
    private Date regDt; // 등록일자
}