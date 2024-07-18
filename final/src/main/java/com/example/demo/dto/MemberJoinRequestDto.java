package com.example.demo.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberJoinRequestDto {
    private String memId;
    private String pw;
    private String name;
    private String email;
    private String role;

    @Builder
    public MemberJoinRequestDto(String memId, String pw, String name, String email, String role) {
        this.memId = memId;
        this.pw = pw;
		this.name = name;
		this.email = email;
		this.role = role;
    }

	public MemberDTO toEntity(PasswordEncoder passwordEncoder) {
		return MemberDTO.builder()
				.memId(memId)
                .name(name)
				.pw(passwordEncoder.encode(pw))
				.email(email)
				.role(role)
                .build()
                ;
	}
}
