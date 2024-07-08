package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 컨트롤러 URI 호출 콘솔 로깅 인터셉터
 */
@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {

    /**
     * 컨트롤러 실행 전에 수행
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
        log.debug("==================== CALL INFO START ====================");
		log.debug("Request URI ===>\t:{}", request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 컨트롤러 수행 후 결과를 뷰로 보내기 전에 수행
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("==================== CALL INFO END ====================");
        // HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 뷰 작업까지 완료된 후 수행
     */
    // @Override
    // public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    //         throws Exception {
    //     // TODO Auto-generated method stub
    //     HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    // }
}
