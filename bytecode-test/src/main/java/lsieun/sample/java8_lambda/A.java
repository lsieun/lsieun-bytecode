package lsieun.sample.java8_lambda;

import java.util.Arrays;
import java.util.Locale;

public class A {
    public static void m() {
        for (final Locale locale : Locale.getAvailableLocales()) {
            System.out.println(locale);
        }
    }

    public static void n() {
        Arrays.stream(Locale.getAvailableLocales()).forEach((locale) -> {
            System.out.println(locale);
        });
    }
}
