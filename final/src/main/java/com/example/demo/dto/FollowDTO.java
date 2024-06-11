package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

// @Data
@ToString
@Component
public class FollowDTO {
    private String uniqueKey;   // 유니크키
    private String memId; // 회원 ID
    private int seq; // 순번
    private String followingMemId; // 팔로잉 회원 ID
    private Date regDt; // 등록일자

    public String getUniqueKey() {
        return this.uniqueKey;
    }

    public String getMemId() {
        return this.memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getFollowingMemId() {
        return this.followingMemId;
    }

    public void setFollowingMemId(String followingMemId) {
        this.followingMemId = followingMemId;
    }

    public Date getRegDt() {
        return this.regDt;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

}