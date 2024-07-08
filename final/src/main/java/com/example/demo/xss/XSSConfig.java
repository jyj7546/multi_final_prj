package com.example.demo.xss;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class XSSConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    /**
     * lucy xss 필터 기본 설정
     * @return
     */
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> xssEscapeServletFilter() {
        try {
            XssEscapeServletFilter filter = new XssEscapeServletFilter();
            FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
            registrationBean.setFilter(filter);
            registrationBean.setName("xssEscapeServletFilter");
            registrationBean.addUrlPatterns("/*");
            registrationBean.setOrder(1);
            
            return registrationBean;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create XssEscapeServletFilter bean", e);
        }
    }

    /**
     * POST requestBody json 파라미터 데이터 xss 필터 추가 설정
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
        ObjectMapper copy = objectMapper.copy();
        copy.getFactory().setCharacterEscapes(new XSSCharacterEscapes());
        return new MappingJackson2HttpMessageConverter(copy);
    }
}
