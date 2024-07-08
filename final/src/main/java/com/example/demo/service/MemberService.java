package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.status.StatusMsg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper mapper;

    public MemberDTO getMember(String memId) {
        return mapper.getMember(memId);
    }

    public int getMemberCnt() {
        return mapper.getMemberCnt();
    }

    public List<Map<String, Object>> getMemberList() {
        return mapper.getMemberList();
    }
    
    public int insertMember(MemberDTO dto) {
        return mapper.insertMember(dto);
    }

    public int deleteMember(String memId) {
        return mapper.deleteMember(memId);
    }

    /**
     * 로그인 서비스
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

    /**
     * 로그인한 회원 Level 조회 서비스
     * @param memId
     * @return
     */
    public String selectMemberLevelByMemId(String memId) {
        return mapper.getMemberLevelByMemId(memId);
    }    
}
