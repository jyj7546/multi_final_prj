package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Component
@Data
public class MemberDTO {
	private String uniqueKey; // 유니크키
	private String id; // 회원 ID
	private String pw; // 비번
	private String name; // 이름
	private String birth; // 생년월일
	private String email; // 이메일
	private Date regDt; // 등록일자
}