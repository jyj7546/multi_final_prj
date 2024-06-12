package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.status.StatusMsg;

@Service
public class MemberService {
    @Autowired
    MemberMapper mapper;

    @Autowired
    MemberDTO memberDTO;

    public MemberDTO getMember(String id) {
        MemberDTO dto = mapper.getMember(id);
        return dto;
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

    public String loginMember(Map<String, Object> param) {
        String result = null;
        MemberDTO dto1 = getMember((String) param.get("id"));    // 먼저 해당 아이디로 회원 존재하는지 조회
        System.out.println("dto1 ::: "+dto1);
        if(dto1 != null) {  // 회원 존재하는지 체크   
            if(dto1.getPw().equals(param.get("pw"))) {   // 비번 맞는지 체크
                result = StatusMsg.USER_LOGIN_SUCC;
            } else {    // 비번 틀린 경우
                result = StatusMsg.USER_LOGIN_FAIL_PW;
            }
        } else {    // 회원 없는 경우
            result = StatusMsg.USER_LOGIN_FAIL_NO_EXISTS; 
        }
        System.out.println("result ::: " + result);

        return result;
    }


    public int deleteMember(String id) {
        return mapper.deleteMember(id);
    }

    
}
