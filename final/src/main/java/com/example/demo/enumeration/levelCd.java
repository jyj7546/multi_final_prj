package com.example.demo.enumeration;

import java.util.Arrays;

public enum levelCd {
    ZERO("0", "0레벨사용자"),
    ONE("1", "1레벨사용자"),
    TWO("2", "2레벨사용자"),
    THREE("3", "3레벨사용자"),
    OTHER("other", "알수없음")
    ;

    private final String code;
    private final String codeName;
    // 값 추가

    levelCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
        // 값 추가
    }

    public String code() {
        return code;
    }

    public String codeName() {
        return codeName;
    }

    /**
     * enum 값 순회 검색
     * @param code
     * @return
     */
    public static levelCd valueOfCode(String code) {
        return Arrays.stream(values())
                    .filter(value -> value.code.equals(code))
                    .findAny()
                    .orElse(null);
    }

    public static levelCd valueOfCodeName(String codeName) {
        return Arrays.stream(values())
                    .filter(value -> value.codeName.equals(codeName))
                    .findAny()
                    .orElse(null);
    }
}
