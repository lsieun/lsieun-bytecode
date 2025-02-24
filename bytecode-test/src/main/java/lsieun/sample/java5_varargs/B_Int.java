package lsieun.sample.java5_varargs;

public class B_Int {
    public void targetMethod(String comment, int... args) {
        //
    }

    public void test() {
        targetMethod("Hello int", 1, 2, 3);
    }
}
