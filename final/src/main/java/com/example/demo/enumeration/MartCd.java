package com.example.demo.enumeration;

import java.util.Arrays;

public enum MartCd {
    EMART("emart", "이마트", "https://emart.ssg.com/"),
    HOMEPLUS("homeplus", "홈플러스", "https://mfront.homeplus.co.kr/"),
    LOTTEMART("lottemart", "롯데마트", "https://www.lotteon.com/p/display/main/lottemart"),
    OTHER("other", "알수없음 ", "127.0.0.1:8080")
    ;

    private final String code;
    private final String codeName;
    private final String url;
    // 값 추가

    MartCd(String code, String codeName, String url) {
        this.code = code;
        this.codeName = codeName;
        this.url = url;
        // 값 추가
    }

    // MartCd.EMART.name(); // "EMART" name()은 Enum 클래스 고정 메소드

    public String code() {
        return code;
    }

    public String codeName() {
        return codeName;
    }

    public String url() {
        return url;
    }

    /**
     * enum 값 순회 검색
     * @param code
     * @return
     */
    public static MartCd valueOfCode(String code) {
        return Arrays.stream(values())
                    .filter(value -> value.code.equals(code))
                    .findAny()
                    .orElse(null);
    }

    public static MartCd valueOfCodeName(String codeName) {
        return Arrays.stream(values())
                    .filter(value -> value.codeName.equals(codeName))
                    .findAny()
                    .orElse(null);
    }

    public static MartCd valueOfUrl(String url) {
        return Arrays.stream(values())
                    .filter(value -> value.url.equals(url))
                    .findAny()
                    .orElse(null);
    }

    public static String getUrlWithCode(String code) throws Exception {
        return Arrays.stream(MartCd.values())
                    .filter(value -> value.code.equals(code))
                    .findFirst()
                    .orElseThrow(() -> new Exception("NO MATCH ERROR"))
                    .url();
    }
}
