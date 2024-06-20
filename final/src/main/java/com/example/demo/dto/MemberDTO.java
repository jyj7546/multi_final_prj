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
	private String memId; // 회원 ID
	private String pw; // 비번
	private String name; // 이름
	private String birth; // 생년월일
	private String email; // 이메일
	private String address; // 주소
	private String telNo; // 전화번호
	private String sex; // 성별
	private String idQuestion; // 아이디찾기질문
	private String pwQuestion; // 비번찾기질문
	private String profileImgPath; // 프로필이미지경로
	private String point; // 포인트
	private String level; // 레벨
	private String nickName; // 닉네임
	private String memStatus; // 회원상태 (정상가입: 1, 휴면: 2, 탈퇴: 3, 비정상: 9)
	private String snsJoinYn; // 소셜가입(로그인)여부
	private Date regDt; // 등록일자
	private Date updateDt; // 업데이트일자
}