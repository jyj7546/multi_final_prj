package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

// @Data
@ToString
@Component
public class CommentDTO {
    private String uniqueKey;   // 유니크키
    private String commentId; // 댓글 ID
    private int seq; // 순번
    private String postId; // 게시글 ID
    private String commentContent; // 댓글내용
    private String subCommentId; // 대댓글 ID
    private Date regDt; // 등록일자

    public String getUniqueKey() {
        return this.uniqueKey;
    }

    public String getCommentId() {
        return this.commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getPostId() {
        return this.postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentContent() {
        return this.commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getSubCommentId() {
        return this.subCommentId;
    }

    public void setSubCommentId(String subCommentId) {
        this.subCommentId = subCommentId;
    }

    public Date getRegDt() {
        return this.regDt;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

}