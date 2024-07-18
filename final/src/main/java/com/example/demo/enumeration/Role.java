package com.example.demo.enumeration;

import java.util.Arrays;

public enum Role {
        NOUSER("nouser", "미로그인사용자"),
        USER("user", "일반사용자"),
        ADMIN("admin", "관리자"),
        OTHER("other", "알수없음")
        ;

        private final String role;
        private final String roleName;

        Role(String role, String roleName) {
            this.role = role;
            this.roleName = roleName;
        }

        public String role() {
            return role;
        }
    
        public String roleName() {
            return roleName;
        }

        public static Role valueOfRole(String role) {
            return Arrays.stream(values())
                        .filter(value -> value.role.equals(role))
                        .findAny()
                        .orElse(null);
        }

        public static Role valueOfRoleName(String roleName) {
            return Arrays.stream(values())
                        .filter(value -> value.roleName.equals(roleName))
                        .findAny()
                        .orElse(null);
        }

        public static String getRoleNameWithRole(String role) throws Exception {
            return Arrays.stream(values())
                        .filter(value -> value.role.equals(role))
                        .findFirst()
                        .orElseThrow(() -> new Exception("NO MATCH ERROR"))
                        .roleName();
        }
}
