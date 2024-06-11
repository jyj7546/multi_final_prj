package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

// @Data
@ToString
@Component
@Data
public class FollowDTO {
    private String uniqueKey;   // 유니크키
    private String memId; // 회원 ID
    private int seq; // 순번
    private String followingMemId; // 팔로잉 회원 ID
    private Date regDt; // 등록일자
}