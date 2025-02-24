package lsieun.sample;

import java.util.function.Function;

public class HelloWorld {
    public static void main(String[] args) {
        Function<Integer, Integer> func = (i) -> i + 5;
        Integer value = func.apply(10);
        System.out.println(value);
    }
}


