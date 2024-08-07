package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MemberDTO;

@Mapper
public interface MemberMapper {
    MemberDTO getMember(String memId);
    int getMemberCnt();
    List<Map<String, Object>> getMemberList();
    int insertMember(MemberDTO dto);
    MemberDTO loginMember(Map<String,Object> param);
    int deleteMember(String memId);
    String getMemberLevelByMemId(String memId);

    // int insertUser(MemberJoinRequestDto memberJoinRequestDto);
}
