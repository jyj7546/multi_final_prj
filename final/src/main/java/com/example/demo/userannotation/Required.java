package com.example.demo.userannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 생성을 강제하여야 하는 변수를 컴파일 시점에 알려주는 어노테이션
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface Required {
    
}
