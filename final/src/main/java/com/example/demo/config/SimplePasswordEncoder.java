// package com.example.demo.config;

// import org.springframework.security.crypto.password.PasswordEncoder;

// public class SimplePasswordEncoder implements PasswordEncoder {

//     /**
//      * 사용자 비밀번호 암호화
//      * rawPassword : 사용자가 입력한 비밀번호
//      */
//     @Override
//     public String encode(CharSequence rawPassword) {
//         return rawPassword.toString();
//     }

//     /**
//      * 실제 비밀번호랑 암호화된 비밀번호 일치한지 비교
//      * loadUserByUsername 에서 UserDetails 에 넣어준 password()가 여기로 들어와서 비번 같은지 자동확인
//      */
//     @Override
//     public boolean matches(CharSequence rawPassword, String encodedPassword) {
//         return encodedPassword.equals(encode(rawPassword));
//     }
    
// }
