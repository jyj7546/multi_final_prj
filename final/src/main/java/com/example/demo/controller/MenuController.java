// package com.example.demo.controller;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.servlet.ModelAndView;

// import jakarta.servlet.http.HttpSession;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;


// @Controller
// @RequiredArgsConstructor
// @Slf4j
// public class MenuController {
//     @Value("${session-key.member-id}")
//     private String sessionMemId;  // 세션 정보(회원ID) 가져오기 위한 고정 String 값 ("SESSION_MEMBER_ID")

//     /**
//      * 각 메뉴 호출시 사용할 컨트롤러
//      * @param menuName: {"mainfeed", "mypage-main", "login", "sign-up", "write", "DM"}
//      * @return
//      */
//     @GetMapping("{menuName}")
//     public ModelAndView goMenu(@PathVariable("menuName") String menuName, HttpSession session) {
//         // System.out.println("==============");
//         // logger.info("first menuName is... " + menuName);
//         // System.out.println("==============");

//         ModelAndView mv = new ModelAndView();

//         if(menuName.equals("login")) {  // 로그인 페이지 호출 시 세션 체크 후 처리
//             // session 체크해서 값 있을 경우, 무조건 mainfeed 페이지 리턴
//             if(session.getAttribute(sessionMemId) != null) {
//                 menuName = "mainfeed";
//                 mv.addObject("message", "이미 로그인되어 있습니다.");
//             }
//         } else {    // 타페이지 호출 시 세션 체크 후 처리
//             // 회원가입 호출 시에는 세션 비어있음 체크 제외
//             if(!menuName.equals("sign-up")) {
//                 // session 체크해서 비어있을 경우, 무조건 login 페이지 리턴
//                 if(session.getAttribute(sessionMemId) == null) {
//                     menuName = "login/login";
//                     // mv.addObject("message", "로그인 먼저 해주세요.");
//                 }
//             }
//         }
        
//         // System.out.println("==============");
//         // logger.info("mv is... " + mv.toString());
//         // logger.info("second menuName is... " + menuName);
//         // System.out.println("==============");

//         mv.setViewName(menuName);

//         return mv;
//     }



    
    
// }
