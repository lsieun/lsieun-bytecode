package lsieun.sample.java8_lambda;

import java.util.function.Consumer;

public class MethodReference_02_BoundInstance {
    public Consumer<String> getConsumer() {
        return System.out::println;
    }
}
