package com.example.demo.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
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
	private String role;	// 권한 {미로그인사용자, 사용자, 관리자}
	private String level; // 레벨 {0,1,2,3}
	private String nickName; // 닉네임
	private String memStatus; // 회원상태 (정상가입: 1, 휴면: 2, 탈퇴: 3, 비정상: 9)
	private String snsJoinYn; // 소셜가입(로그인)여부
	private Date regDt; // 등록일자
	private Date updateDt; // 업데이트일자

	@Builder
	public MemberDTO(String memId, String pw, String name, String birth, String email, String address, String telNo, String sex, String idQuestion, String pwQuestion, String profileImgPath, String point, String role, String level, String nickName, String memStatus, String snsJoinYn) {
		this.memId = memId;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.address = address;
		this.telNo = telNo;
		this.sex = sex;
		this.idQuestion = idQuestion;
		this.pwQuestion = pwQuestion;
		this.profileImgPath = profileImgPath;
		this.point = point;
		this.role = role;
		this.level = level;
		this.nickName = nickName;
		this.memStatus = memStatus;
		this.snsJoinYn = snsJoinYn;
	}
}