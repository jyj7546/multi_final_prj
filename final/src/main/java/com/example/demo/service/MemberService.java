package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.enumeration.Role;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.status.StatusMsg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public MemberDTO getMember(String memId) {
        return mapper.getMember(memId);
    }

    public int getMemberCnt() {
        return mapper.getMemberCnt();
    }

    public List<Map<String, Object>> getMemberList() {
        return mapper.getMemberList();
    }

    public int deleteMember(String memId) {
        return mapper.deleteMember(memId);
    }

    /**
     * 로그인한 회원 Level 조회 서비스
     * @param memId
     * @return
     */
    public String selectMemberLevelByMemId(String memId) {
        return mapper.getMemberLevelByMemId(memId);
    } 
    
    /**
     * MemberDTO 빌더패턴으로 생성하여 아이디로 권한체크해서 분기 저장 
     * @param dto
     * @return
     */
    public int insertMember(MemberDTO dto) {
        MemberDTO memberDTO;
        // String upperMemId = dto.getMemId().toUpperCase();

        // 관리자 가입은 DB로 수기로 넣기(화면단에서 막음)
        // if(upperMemId.startsWith(Role.ADMIN.role())) {  // 관리자로 회원가입 시(memId가 adminXXX)
        //     log.debug("관리자 가입");
        //     memberDTO = MemberDTO.builder()
		// 		.memId(dto.getMemId())
        //         .name(dto.getName())
		// 		.pw(passwordEncoder.encode(dto.getPw()))
		// 		.email(dto.getEmail())
		// 		.role(Role.ADMIN.name())
        //         .build()
        //         ;
        // } else {
            // log.debug("일반사용자 가입");
            memberDTO = MemberDTO.builder()
				.memId(dto.getMemId())
                .name(dto.getName())
				.pw(passwordEncoder.encode(dto.getPw()))
				.email(dto.getEmail())
				.role(Role.USER.name()) // 최초 가입시 USER 권한
                .build()
                ;
        // }
        
        return mapper.insertMember(memberDTO);
    }

    /**
     * 로그인 조회 서비스
     * 스프링 시큐리티 MyUserDetailsService 구현체로 대체
     *
     * @param param
     * @return
     */
    public String loginMember(Map<String, Object> param) {
        String result = null;
        MemberDTO dto = getMember((String) param.get("memId"));    // 먼저 해당 아이디로 회원 존재하는지 조회
        log.debug("조회된 회원 정보 ===>\t{}", dto);
        if(dto != null) {  // 회원 존재하는지 체크   
            if(dto.getPw().equals(param.get("pw"))) {   // 비번 맞는지 체크
                result = StatusMsg.USER_LOGIN_SUCC;
            } else {    // 비번 틀린 경우
                result = StatusMsg.USER_LOGIN_FAIL_PW;
            }
        } else {    // 회원 없는 경우
            result = StatusMsg.USER_LOGIN_FAIL_NO_EXISTS; 
        }
        log.debug("LOGIN RESULT ===>\t{}", result);

        return result;
    }

       
}
