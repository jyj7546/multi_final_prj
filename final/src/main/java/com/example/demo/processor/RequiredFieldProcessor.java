package com.example.demo.processor;

import com.example.demo.userannotation.Required;
import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * @Required 어노테이션 정의 클래스
 * 생성을 강제하는 변수를 컴파일 시점에 알려줌
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.example.demo.annotation.Required")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class RequiredFieldProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Required.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "@Required can only be applied to fields", element);
                return true;
            }

            Element enclosingElement = element.getEnclosingElement();
            if (enclosingElement.getKind() != ElementKind.CLASS) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "@Required field must be inside a class", element);
                return true;
            }

            boolean hasBuilder = enclosingElement.getEnclosedElements().stream()
                    .anyMatch(e -> e.getSimpleName().toString().equals("builder") &&
                            e.getKind() == ElementKind.METHOD);

            if (!hasBuilder) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "Class containing @Required field must have a builder", enclosingElement);
                return true;
            }
        }
        return true;
    }
}
