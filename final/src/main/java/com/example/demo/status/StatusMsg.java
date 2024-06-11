package com.example.demo.status;

/**
 * 상태값 세분화 static 변수 정의 (컨트롤러단에서 이 값으로 체크해서 프론트로 상태값 분기해서 전달)
 */
public class StatusMsg {
    public static final String UNKNOWN_ERR = "알 수 없는 오류 발생.";
    public static final String BAD_REQUEST = "부적절한 요청.";
    public static final String NOT_FOUND = "찾을 수 없음.";
    public static final String INTERNAL_SERVER_ERROR = "서버 오류 발생.";
    public static final String CREATED = "생성 성공.";
    public static final String OK = "응답 성공.";

    public static final String USER_LOGIN_SUCC = "로그인 성공.";
    public static final String USER_LOGIN_FAIL = "로그인 실패.";
    public static final String USER_LOGIN_FAIL_NO_EXISTS = "존재하지 않는 회원입니다.";
    public static final String USER_LOGIN_FAIL_PW = "비밀번호가 틀립니다.";
    
    public static final String USER_JOIN_SUCC = "회원가입 성공.";
    public static final String USER_JOIN_FAIL = "회원가입 실패.";
    public static final String USER_QUIT_SUCC = "회원탈퇴 성공.";
    public static final String USER_QUIT_FAIL = "회원탈퇴 실패.";
    public static final String ALREADY_EXISTS_USER = "이미 존재하는 회원입니다.";

    public static final String GET_USER_SUCC = "회원조회 성공.";
    public static final String GET_USER_FAIL = "회원조회 실패.";
    public static final String NO_EXISTS_USER = "존재하지 않는 회원입니다.";

    public static final String WRITE_POST_SUCC = "글작성 성공.";
    public static final String WRITE_POST_FAIL = "글작성 실패.";
    public static final String DELETE_POST_SUCC = "글삭제 성공.";
    public static final String DELETE_POST_FAIL = "글삭제 실패.";

    public static final String WRITE_COMMENT_SUCC = "댓글작성 성공.";
    public static final String WRITE_COMMENT_FAIL = "댓글작성 실패.";
    public static final String DELETE_COMMENT_SUCC = "글삭제 성공.";
    public static final String DELETE_COMMENT_FAIL = "글삭제 실패.";

}
