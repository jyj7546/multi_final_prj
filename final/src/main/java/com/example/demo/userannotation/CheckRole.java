package com.example.demo.userannotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

@Target(ElementType.METHOD) // 메소드에만 부착가능
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckRole {
    Role[] role() default {Role.USER};

    enum Role {
        NOUSER("nouser", "미로그인사용자"),
        USER("user", "일반사용자"),
        ADMIN("admin", "관리자"),
        ONE("1", "1레벨사용자"),
        TWO("2", "2레벨사용자"),
        THREE("3", "3레벨사용자"),
        OTHRE("other", "알수없음")
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
}
