package com.example.demo.enumeration;

import java.util.Arrays;

public enum BoardCd {
    BASIC("basic", "기본세일정보게시판"),
    USER("user", "유저세일정보게시판"),
    NOTICE("notice", "공지게시판"),
    REVIEW("review", "리뷰게시판"),
    OTHER("other", "알수없음")
    ;

    private final String code;
    private final String codeName;
    // 값 추가 가능

    BoardCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
        // 값 추가 가능
    }

    public String code() {
        return code;
    }

    public String codeName() {
        return codeName;
    }

    /**
     * enum values() 순회하며 검색
     * @param code
     * @return
     */
    public static BoardCd valueOfCode(String code) {
        return Arrays.stream(values())
                    .filter(value -> value.code.equals(code))
                    .findAny()
                    .orElse(null);
    }

    public static BoardCd valueOfCodeName(String codeName) {
        return Arrays.stream(values())
                    .filter(value -> value.codeName.equals(codeName))
                    .findAny()
                    .orElse(null);
    }

    public static String getCodeNameWithCode(String code) throws Exception {
        return Arrays.stream(BoardCd.values())
                    .filter(value -> value.code.equals(code))
                    .findFirst()
                    .orElseThrow(() -> new Exception("NO MATCH ERROR"))
                    .codeName();
    }
}
