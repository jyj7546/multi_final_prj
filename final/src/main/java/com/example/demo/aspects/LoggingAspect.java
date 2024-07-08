package com.example.demo.aspects;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 컨트롤러 패키지 로깅 AOP
 * Around만 활성화
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {
    
    /**
     * 컨트롤러 패키지 메소드 실행 전
     * @param joinPoint
     */
    // @Before("execution(* com.example.demo.controller.*.*(..))")
    // public void logBefore(JoinPoint joinPoint) {
    //     log.info("Before ===> {}", joinPoint.getSignature().getName());
    // }

    /**
     * 컨트롤러 패키지 메소드 실행 후
     *
     * @param joinPoint
     */
    // @After("execution(* com.example.demo.controller.*.*(..))")
    // public void logAfter(JoinPoint joinPoint) {
    //     log.info("After: " + joinPoint.getSignature().getName());
    // }

    /**
     * 컨트롤러 패키지 메소드 정상 실행 후 
     * @param joinPoint
     * @param result (HTTP 응답코드)
     */
    // @AfterReturning(pointcut = "execution(* com.example.demo.controller.*.*(..))", returning = "result")
    // public void logAfterReturning(JoinPoint joinPoint, Object result) {
    //     log.info("AfterReturning: " + joinPoint.getSignature().getName() + " result: " + result);
    // }

    /**
     * 컨트롤러 패키지 메소드 예외 발생 시
     * @param joinPoint
     * @param e
     */
    // @AfterThrowing(pointcut = "execution(* com.example.demo.controller.*.*(..))", throwing = "e")
    // public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    //     log.info("AfterThrowing: " + joinPoint.getSignature().getName() + " exception: " + e.getMessage());
    // }

    /**
     * 컨트롤러 패키지 메소드 실행 전, 후, 예외 발생 시
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    // @Around("execution(* com.example.demo.controller.*.*(..))")
    // public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    //     log.info("Around before: " + joinPoint.getSignature().getName());
    //     Object result = joinPoint.proceed();
    //     log.info("Around after: " + joinPoint.getSignature().getName());
    //     return result;
    // }


    /**
     * 컨트롤러, 서비스, 매퍼 패키지 확인 후 전체를 순서대로 로깅
     * 호출한 컨트롤러 URI는 인터셉터로 로깅
     * com.example.demo의 모든 하위 패키지 중 xxxController, xxxService, xxxMapper 패턴의 이름을 가진 클래스에서 파라미터가 0개 이상인 메서드에 대해 적용
     */
    @Around("execution(* com.example.demo..*Controller.*(..)) || execution(* com.example.demo..*Service.*(..)) || execution(* com.example.demo..*Mapper.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        
        String className = joinPoint.getSignature().getDeclaringTypeName();  // 
        String mvcType =
                StringUtils.contains(className, "Controller") ? "Controller ===>\t" :
                StringUtils.contains(className, "Service") ? "Service ===>\t" :
                StringUtils.contains(className, "Mapper") ? "Mapper ===>\t" :
                "CLASS NAME PATTERN IS NOT ALLOWED ===>\\t";

        log.debug("{}{}()", mvcType, joinPoint.getSignature().getName());
        return joinPoint.proceed();
    }
    
}
