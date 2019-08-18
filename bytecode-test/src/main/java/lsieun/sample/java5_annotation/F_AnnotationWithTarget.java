package lsieun.sample.java5_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
public @interface F_AnnotationWithTarget {
}
