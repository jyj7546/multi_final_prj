package com.example.demo.status;

import org.springframework.http.HttpStatus;

import com.example.demo.userannotation.Required;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyHttpResponse {
    @Required
    private HttpStatus status;
    private String message;
    private Object data;

    // /**
    //  * 전체 파라미터 받는 생성자 추가 생성
    //  * @param status
    //  * @param message
    //  * @param data
    //  */
    // public MyHttpResponse(HttpStatus status, String message, Object data) {
    //     this.status = status;
    //     this.message = message;
    //     this.data = data;
    // }
}
