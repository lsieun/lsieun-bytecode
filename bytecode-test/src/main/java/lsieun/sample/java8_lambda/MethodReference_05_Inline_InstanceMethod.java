package lsieun.sample.java8_lambda;

import java.util.function.Supplier;

public class MethodReference_05_Inline_InstanceMethod {
    public String provideMessage(String message) {
        return message;
    }

    public Supplier<String> getSupplier(String message) {
        return () -> this.provideMessage(message);
    }
}
