package lsieun.sample.java1_inner_classes;

public class A_Enclosing {
    private static int i;
    public static class StaticNested {
        public void test() {
            int sum = i;
        }
    }
}
