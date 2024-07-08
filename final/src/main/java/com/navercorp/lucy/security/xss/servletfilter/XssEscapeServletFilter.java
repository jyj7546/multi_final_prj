package com.navercorp.lucy.security.xss.servletfilter;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * XSSConfig.java 파일의 FilterRegistrationBean<XssEscapeServletFilter> 메소드에 
 * XssEscapeServletFilter 파일이 상속받는 Filter 클래스는 javax.servlet 라 미스매치 에러
 * jakarta.servlet 패키지의 Filter를 상속받게 변경함
 * (디펜던시로 받아온 전체 클래스에 본 파일과 Wrapper클래스로 덮어씀)
 */
public class XssEscapeServletFilter implements Filter {
    private XssEscapeFilter xssEscapeFilter = XssEscapeFilter.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssEscapeServletFilterWrapper(request, xssEscapeFilter), response);
    }

    @Override
    public void destroy() {
    }
}
