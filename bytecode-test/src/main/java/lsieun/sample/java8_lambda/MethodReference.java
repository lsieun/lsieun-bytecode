package lsieun.sample.java8_lambda;

import java.util.function.Supplier;

public class MethodReference {
    public static String lambdiseMe() {
        return "Hello World!";
    }

    public static Supplier<String> getSupplier() {
        return MethodReference::lambdiseMe;
    }
}
