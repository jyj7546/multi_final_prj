package com.example.demo.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

// @Data
@ToString
@Component
@Data
public class TestDTO {
    private String id;
    private String name;
    private int pw;
    private String phone;
    private String email;
}