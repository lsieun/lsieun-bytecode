package lsieun.sample.java5_varargs;

public class A_String {
    public void targetMethod(String comment, String... args) {
        //
    }

    public void test() {
        targetMethod("Hello String", "A", "B", "C");
    }

    public void test_empty() {
        targetMethod("Hello String");
    }
}
