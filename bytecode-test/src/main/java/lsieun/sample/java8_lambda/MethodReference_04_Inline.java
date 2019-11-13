package lsieun.sample.java8_lambda;

import java.util.function.Supplier;

public class MethodReference_04_Inline {
    public Supplier<String> getSupplier() {
        return () -> "Hello World!";
    }
}
