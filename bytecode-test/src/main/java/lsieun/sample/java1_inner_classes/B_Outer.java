package lsieun.sample.java1_inner_classes;

public class B_Outer {
    private int i;
    public class Inner {
        public void test() {
            int sum = B_Outer.this.i;
        }
    }
}
