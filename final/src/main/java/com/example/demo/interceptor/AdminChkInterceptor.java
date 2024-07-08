package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.MemberService;
import com.example.demo.userannotation.CheckRole;
import com.example.demo.userannotation.CheckRole.Role;
import com.google.common.base.Objects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminChkInterceptor implements HandlerInterceptor {
    @Value("${session-key.member-id}")
    private String sessionMemId;  // 세션 정보(회원ID) 가져오기 위한 고정 String 값 ("SESSION_MEMBER_ID")

    private final MemberService memberService;

   /*  @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                log.debug("==================== ROLE CHECK INTERCEPTOR ====================");
                log.debug("==================== START ====================");
                if(!(handler instanceof HandlerMethod)) {
                    return true;
                }
                // Controller에 있는 메서드인지 확인하기 위해 HandlerMethod 타입인지 체크
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                
                CheckRole checkRole = handlerMethod.getMethodAnnotation(CheckRole.class);
                if(checkRole == null) return true;  //@CheckRole 이 부착되어있는지 체크

                // 스프링 시큐리티 적용 후 가능
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if(!authentication.isAuthenticated()) return false;  // 로그인 하지 않았다면 false 리턴
                String email = authentication.getName();
                // 스프링 시큐리티 적용 후 가능

                String level = memberService.selectMemberLevelByMemId(memId);
                log.info("# checkMemberRoleDto = {}", checkMemberRoleDto);
                if(Objects.equals(checkMemberRoleDto.getRole(), "ADMIN")) return true;
                // 관리자면 패스

                return true;
    } */

    /**
     * 로그인 직후 전역 세션 정보(memId) 받아오고 그걸로 ADMIN 권한 체크 인터셉터
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
                log.debug("==================== ROLE CHECK INTERCEPTOR ====================");
                log.debug("==================== START ====================");
                
                // handler타입 HandlerMethod X -> 패스 (Controller 패키지 메서드인지 확인)
                if(!(handler instanceof HandlerMethod)) {
                    log.debug("NOT CONTROLLER PACKAGE");
                    //TODO: 후처리
                }
                
                // @CheckRole 어노테이션 부착 X -> 패스
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                CheckRole checkRole = handlerMethod.getMethodAnnotation(CheckRole.class);
                if(checkRole == null) {
                    log.debug("NO @CheckRole ANNOTATION");
                    //TODO: 후처리
                }

                // 세션의 회원ID 체크
                HttpSession session = request.getSession(false);
                if(session != null) {
                    String memId = (String) session.getAttribute(sessionMemId);
                    String level = memberService.selectMemberLevelByMemId(memId);
                    log.info("LOGIN MEMBER'S LEVEL ===> {}", level);

                    if(level.equals(Role.ADMIN.role())) {   // level이 "admin" 인지 체크
                        log.debug("ADMIN OK");
                        response.sendRedirect("/admin");    //FIXME: 후처리(관리자 페이지 호출)
                    } else {
                        log.debug("NOT ADMIN");
                        response.sendRedirect("/use");  //FIXME: 후처리(유저 페이지 호출)
                    }
                } else { // 세션이 null인 경우 에러
                    log.error("SESSION IS NULL");
                    response.sendRedirect("/login");    //FIXME: 후처리(로그인 페이지 호출)
                }
    }
}
