package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.PrintWriter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.enumeration.Role;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.status.MyHttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity  // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    protected WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/favicon.ico", "/", "/login/**", "/login/login");
    }

    /**
     * 비밀번호 암호화 빈 주입
     * @return
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
        // return new MessageDigestPasswordEncoder("SHA-256");
        return new BCryptPasswordEncoder(); // 상대적으로 강력한 암호화방식으로 변경
    }

    /**
     * URL 에 적용될 스프링 시큐리티 필터체인
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 사이트 위변조 요청 방지
            .csrf((csrfconfig) -> 
                    csrfconfig.disable()    // Cross site Request forgery 옵션 끔
            )
            // 웹에서 h2(DB)콘솔 사용가능하게 하는 설정
            // .headers((headerConfig) -> 
            //         headerConfig.frameOptions(frameOptionsConfig -> 
            //                 frameOptionsConfig.sameOrigin()    // frame iframe 태그로 페이지 표현하는 부분 표시 가능하게 해당 설정 변경 (Spring Security는 Clickjacking 공격보호를 위해 기본적으로 X-Frame-Options 응답해더를 DENY)
            //         )
            // )
            // XSS 공격 방지 설정 추가
            .headers(headersConfig -> 
                    headersConfig
                        .xssProtection(withDefaults())  // X-XSS-Protection 헤더를 기본값으로 활성화
            )
            // 인가(접근권한) 설정
            .authorizeHttpRequests((authorizeRequests) ->
                    authorizeRequests
                            .requestMatchers("/", "/login/**", "/login/login").permitAll()  // 모든 권한 접근 가능한 URI(회원가입, 로그인, 메인화면 등)
                            .requestMatchers("/user/**", "/api/user/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name()) // 유저, 관리자 권한 접근 가능
                            .requestMatchers("/admin/**", "/api/admin/**").hasRole(Role.ADMIN.name())  // 관리자 권한만 접근 가능한 URI
                            .anyRequest().authenticated()
            )
            // 예외처리 추가 설정
            .exceptionHandling((exceptionConfig) -> exceptionConfig.authenticationEntryPoint(unauthorizedEntryPoint)
            .accessDeniedHandler(accessDeniedHandler)   // 401, 403 예외처리
            )
            // 로그인 설정
            .formLogin((formLogin) -> 
                formLogin   // 로그인 설정
                    .loginPage("/login/login")  // 로그인화면 uri 설정
                    .usernameParameter("memId")  // 로그인 아이디 json 키값(미설정시 default: "username")
                    .passwordParameter("pw")  // 로그인 패스워드 json 키값 (미설정시 default: "password")
                    .loginProcessingUrl("/login/login-proc")    // 로그인 submit 요청 받을 uri => myUserDetailsService 메소드 user 객체 생성
                    .defaultSuccessUrl("/mypage-main", true)   // 로그인 성공시 이동할 uri
                    .failureUrl("/error?loginfail")
            )
            // 로그아웃 설정
            .logout((logoutConfig) ->
                logoutConfig
                    .invalidateHttpSession(true)    // 세션 제거
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login/login")  // 로그아웃 성공시 이동할 uri
            )
            // 사용자 인증 처리 컴포넌트 서비스 등록
            .userDetailsService(myUserDetailsService);  // login submit 요청 => /login/login-proc 요청 => 스프링 시큐리티는 이 로직 수행 (유저 객체 생성)

        return http.build();
    }

    /**
     * 401 예외 처리 
     */
    private final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                MyHttpResponse fail = MyHttpResponse.builder()
                                                    .message("Spring security unauthorized...(401)")
                                                    .build();
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };
    /**
     * 403 예외 처리
     */
    private final AccessDeniedHandler accessDeniedHandler =
            (request, response, accessDeniedException) -> {
                MyHttpResponse fail = MyHttpResponse.builder()
                                                    .status(HttpStatus.FORBIDDEN)
                                                    .message("Spring security forbidden...(403)")
                                                    .build();
                response.setStatus(HttpStatus.FORBIDDEN.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };
}
