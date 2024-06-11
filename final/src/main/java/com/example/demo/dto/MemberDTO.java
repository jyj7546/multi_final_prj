package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

// @Data
@ToString
@Component
public class MemberDTO {
    private String uniqueKey;   // 유니크키
	private String memId; // 회원 ID
	private String memPw; // 비번
	private String memName; // 이름
	private String memBirth; // 생년월일
	private String memEmail; // 이메일
	private Date memRegTime; // 등록일자

	public String getUniqueKey() {
        return this.uniqueKey;
    }

	public String getMemId() {
		return this.memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return this.memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemName() {
		return this.memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemBirth() {
		return this.memBirth;
	}

	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}

	public String getMemEmail() {
		return this.memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public Date getMemRegTime() {
		return this.memRegTime;
	}

	public void setMemRegTime(Date memRegTime) {
		this.memRegTime = memRegTime;
	}

}