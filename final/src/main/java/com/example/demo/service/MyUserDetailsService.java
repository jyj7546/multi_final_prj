package com.example.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

/**
 * 스프링시큐리티 로그인 처리 구현체
 */
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    /**
     * 로그인 처리 (필수 구현 메소드)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberMapper.getMember(username);

        if(memberDTO == null){
			throw new UsernameNotFoundException(username);
		}

        // 비밀번호 체크는 AuthenticationProvider 구현체에서 authenticate() 메소드를 통해 passwordEncoder.matches()로 자동 처리됨

        return User.builder()
                    .username(memberDTO.getMemId())
                    .password(memberDTO.getPw())
                    .roles(memberDTO.getRole())
                    .build();
    }

    /**
     * 회원가입 처리
     * @param memberJoinRequestDto
     * @return
     */
    // public int insertUser(MemberJoinRequestDto memberJoinRequestDto) {
    //     return memberMapper.insertMember(memberJoinRequestDto.toEntity(passwordEncoder));
    // }
}