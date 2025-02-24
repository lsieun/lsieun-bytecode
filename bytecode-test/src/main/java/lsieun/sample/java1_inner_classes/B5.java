package lsieun.sample.java1_inner_classes;

public class B5 {
    private class InnerClass {
        private int value;
        private int value2;
    }

    private void test() {
        InnerClass innerClass = new InnerClass();
        innerClass.value = 100;
        int i = innerClass.value;
        i = innerClass.value2;
    }
}
