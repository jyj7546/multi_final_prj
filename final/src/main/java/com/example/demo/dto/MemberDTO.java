package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

// @Data
@ToString
@Component
@Data
public class MemberDTO {
    private String uniqueKey;   // 유니크키
	private String memId; // 회원 ID
	private String memPw; // 비번
	private String memName; // 이름
	private String memBirth; // 생년월일
	private String memEmail; // 이메일
	private Date memRegTime; // 등록일자
}