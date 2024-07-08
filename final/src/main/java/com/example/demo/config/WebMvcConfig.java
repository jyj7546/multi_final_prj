package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.LoggerInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoggerInterceptor loggerInterceptor;

    /**
     * 인터셉터 빈으로 등록
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new LoggerInterceptor()).excludePathPatterns("/static/**");
        registry.addInterceptor(loggerInterceptor).excludePathPatterns("/css/**", "/img/**", "/js/**", "/favicon.ico");
        // WebMvcConfigurer.super.addInterceptors(registry);
    }
}
