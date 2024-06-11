package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.MySession;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import com.example.demo.status.StatusMsg;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

    // private static final Logger logger = LoggerFactory.getLogger(TestController.class);s

    @Autowired
    MemberService memberService;

    /**
     * 회원아이디로 회원 데이터 조회
     * @param memId
     * @return
     */
    @GetMapping("getMember/{memId}")
    @ResponseBody
    public ResponseEntity<Object> getMember(@PathVariable String memId) {
        MemberDTO dto = memberService.getMember(memId);
        
        // MakeStatus status = new MakeStatus();
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // if(dto != null) {
        //     status.setResponseStatus(HttpStatus.OK);
        //     status.setResponseMessage(StatusMsg.GET_USER_SUCC);
        //     status.setResponseData(dto);
        // } else {
        //     status.setResponseStatus(HttpStatus.NO_CONTENT);
        //     status.setResponseMessage(StatusMsg.NO_EXISTS_USER);
        //     status.setResponseData(null);
        // }

        // return new ResponseEntity(status, headers, HttpStatus.OK);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(dto);
    }

    /**
     * 전체 회원 데이터 조회 - RESTAPI로 http상태랑 데이터만 리턴
     * @return
     */
    // @GetMapping("getMemberAll")
    // @ResponseBody
    // public ResponseEntity<Object> getMemberList() {
    //     // List<MemberDTO> list = memberService.getMemberList();
    //     // MakeStatus message = new MakeStatus();
    //     // HttpHeaders headers = new HttpHeaders();
    //     // headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

    //     // message.setResponseStatus(StatusCode.OK);
    //     // message.setResponseMessage(StatusMsg.GET_USER_SUCC);
    //     // message.setResponseData(list);

    //     // return new ResponseEntity<>(message, headers, HttpStatus.OK);
    //     // return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list.toString());
    // }

    /**
     * 실제 DB 데이터 모델앤뷰 리턴 테스트
     * @param mv
     * @return
     */
    @GetMapping("getMemberAll2")
    public ModelAndView getMemberList2() {
        ModelAndView mv = new ModelAndView("member/paramtest");

        List<Map<String, Object>> listMap = memberService.getMemberList();
        mv.setViewName("test2");    // 뷰페이지 
        mv.addObject("result", listMap.toString());   // 데이터

        return mv;
    }

    /**
     * 회원가입
     * 구현완료
     * @param dto
     * @return
     */
    @PostMapping("insertMember")
    @ResponseBody
    public ResponseEntity<Object> insertMember(@RequestBody MemberDTO dto) {    // dto: jsp(view)로부터 전달받은 데이터(회원가입 form 데이터 @RequestBody어노테이션 사용으로 MemberDTO 타입으로 자동매핑됨. 대신 DTO 변수명과 jsp파일 동일해야 함)
        int result = memberService.insertMember(dto);

        if(result > 0) {    // 인서트 성공시 1 리턴됨
            System.out.println("=====================");
            System.out.println("=====================");
            System.out.println("DB 인서트 성공");
            System.out.println("=====================");
            System.out.println("=====================");
            return new ResponseEntity<Object>(StatusMsg.USER_JOIN_SUCC, HttpStatus.CREATED);
        } else {
            System.out.println("=====================");
            System.out.println("=====================");
            System.out.println("DB 인서트 에러");
            System.out.println("=====================");
            System.out.println("=====================");
            return new ResponseEntity<Object>(StatusMsg.USER_JOIN_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 로그인
     * 구현완료
     * @param dto
     * @param request
     * @return
     */
    @PostMapping("loginMember")
    @ResponseBody
    public ResponseEntity<Object> loginMember(@RequestBody MemberDTO dto, HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        param.put("memId", dto.getMemId());
        param.put("memPw", dto.getMemPw());
        String status = memberService.loginMember(param);

        if(status.equals(StatusMsg.USER_LOGIN_SUCC)) {  // 로그인 성공
            HttpSession session = request.getSession(true);
            session.setAttribute(MySession.LOGIN_MEMBER, dto.getMemId());
            System.out.println("session ::: "+session.getAttribute(MySession.LOGIN_MEMBER));

            return new ResponseEntity<Object>(StatusMsg.USER_JOIN_SUCC, HttpStatus.OK);
        } else if(status.equals(StatusMsg.USER_LOGIN_FAIL_NO_EXISTS)) { // 미존재 회원

            return new ResponseEntity<Object>(StatusMsg.USER_LOGIN_FAIL_NO_EXISTS, HttpStatus.NO_CONTENT);
        } else if(status.equals(StatusMsg.USER_LOGIN_FAIL_PW)) {    // 비밀번호 틀림
            return new ResponseEntity<Object>(StatusMsg.USER_LOGIN_FAIL_NO_EXISTS, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        } else {    // 나머지 상태 => 오류로 간주
            return new ResponseEntity<Object>(StatusMsg.USER_JOIN_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 클라이언트 세션 요청 처리
     * 구현완료
     * @param dto
     * @param request
     * @return
     */
    @PostMapping("getSessionMemId")
    @ResponseBody   // 자바객체를 HTTP요청의 바디내용으로 (JSON형식으로)매핑하여 클라이언트로 전송하기 위한 어노테이션
    public ResponseEntity<Object> getSessionMemId(HttpSession session) {
            if (session.getAttribute(MySession.LOGIN_MEMBER) != null) { // 세션 null이 아닌 경우에만
                String sessionMemId = (String) session.getAttribute(MySession.LOGIN_MEMBER);    // 세션 Stirng 형변환
                return ResponseEntity.ok(sessionMemId); // 프론트에게 OK(200)상태와 함꼐 세션아이디 String으로 바디에 넣어서 전달
            } else {
                return ResponseEntity.ok(null);
            }
    }

    /**
     * 로그아웃
     * 구현완료
     * @param request
     * @return
     */
    @GetMapping("logoutMember")
    public String logoutMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);    // getSession(false): 세션 존재시 리턴, 없으면 생성X and null 리턴
        if(session != null) {   // 세션 null이 아닌 경우에만
            session.invalidate();   // 세션 초기화
        }
        
        return "login"; // 로그인 페이지로 이동
    }

    /**
     * 회원탈퇴
     * 구현미완
     * @param memId
     * @param request
     * @param response
     * @param dto
     * @param model
     * @throws Exception
     */
    @PostMapping("deleteMember")
    @ResponseBody
	public ResponseEntity<Object> deleteMember(@RequestBody String memId) {
	    int result = memberService.deleteMember(memId);

        if(result > 0) {
            return new ResponseEntity<Object>(StatusMsg.USER_QUIT_SUCC, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Object>(StatusMsg.USER_QUIT_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
    
}
