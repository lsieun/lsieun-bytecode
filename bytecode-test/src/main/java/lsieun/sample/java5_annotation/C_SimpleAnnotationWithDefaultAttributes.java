package lsieun.sample.java5_annotation;

import lsieun.sample.java5_enum.DaysOfTheWeek;

public @interface C_SimpleAnnotationWithDefaultAttributes {
    int order() default 0; // int类型

    String name() default "Hi, Good"; // String类型

    DaysOfTheWeek day() default DaysOfTheWeek.FRIDAY; // 枚举类型

    Class<?> clazz() default String.class; // Class类型

    int[] array() default {1, 3, 4}; // int类型的数组

    DaysOfTheWeek[] days() default {DaysOfTheWeek.FRIDAY, DaysOfTheWeek.SUNDAY}; // 枚举类型的数组
}
