package lsieun.sample.java1_inner_classes;

public class D3 {
    public void test() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };
        r.run();
    }
}
