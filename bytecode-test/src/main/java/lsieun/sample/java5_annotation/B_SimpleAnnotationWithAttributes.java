package lsieun.sample.java5_annotation;

public @interface B_SimpleAnnotationWithAttributes {
    String name();

    int order() default 0;
}
