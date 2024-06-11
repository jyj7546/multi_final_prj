package com.example.demo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

// @Data
@ToString
@Component
@Data
public class CommentDTO {
    private String uniqueKey;   // 유니크키
    private String commentId; // 댓글 ID
    private int seq; // 순번
    private String postId; // 게시글 ID
    private String commentContent; // 댓글내용
    private String subCommentId; // 대댓글 ID
    private Date regDt; // 등록일자
}