package lsieun.sample.exception;

import java.io.IOException;

public class Try_Catch_Finally {
    public static int test(int a, int b) {
        try {
            return a / b;
        }
        catch (ArithmeticException ex) {
            return 10;
        }
        finally {
            //return 20;
            System.out.println("HelloWorld");
        }
    }

    public static String doSomething() {
        String name = "David";
        try {
            throw new IOException();
        } finally {
            return name;
        }
    }

    public static void main(String[] args) {
        String value = doSomething();
        System.out.println(value);
    }
}
