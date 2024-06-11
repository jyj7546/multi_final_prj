package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

// @Data
@ToString
@Component
public class PostDTO {
    private String uniqueKey;   // 유니크키
    private String postId; // 게시글 ID
    private int seq; // 순번
    private String memId; // 회원 ID
    private int likeCnt; // 좋아요수
    private String imgSrc; // 이미지 경로
    private String contents; // 내용
    private String hashtag; // 해쉬태그
    private Date regDt; // 등록일자

    public String getUniqueKey() {
        return this.uniqueKey;
    }

    public String getPostId() {
        return this.postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getMemId() {
        return this.memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public int getLikeCnt() {
        return this.likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getHashtag() {
        return this.hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Date getRegDt() {
        return this.regDt;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

}