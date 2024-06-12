package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.MySession;

import jakarta.servlet.http.HttpSession;


@Controller
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * 각 메뉴 호출시 사용할 컨트롤러
     * @param menuName: {"mainfeed", "mypage-main", "login", "sign-up", "write", "DM"}
     * @return
     */
    @GetMapping("{menuName}")
    public ModelAndView goMenu(@PathVariable("menuName") String menuName, HttpSession session) {
        System.out.println("==============");
        logger.info("first menuName is... " + menuName);
        System.out.println("==============");

        ModelAndView mv = new ModelAndView();

        if(menuName.equals("login")) {  // 로그인 페이지 호출 시 세션 체크 후 처리
            // session 체크해서 값 있을 경우, 무조건 mainfeed 페이지 리턴
            if(session.getAttribute(MySession.LOGIN_MEMBER) != null) {
                menuName = "mainfeed";
                mv.addObject("message", "이미 로그인되어 있습니다.");
            }
        } else {    // 타페이지 호출 시 세션 체크 후 처리
            // 회원가입 호출 시에는 세션 비어있음 체크 제외
            if(!menuName.equals("sign-up")) {
                // session 체크해서 비어있을 경우, 무조건 login 페이지 리턴
                if(session.getAttribute(MySession.LOGIN_MEMBER) == null) {
                    menuName = "login";
                    mv.addObject("message", "로그인 먼저 해주세요.");
                }
            }
        }
        
        System.out.println("==============");
        logger.info("mv is... " + mv.toString());
        logger.info("second menuName is... " + menuName);
        System.out.println("==============");

        mv.setViewName(menuName);

        return mv;
    }
    
}
