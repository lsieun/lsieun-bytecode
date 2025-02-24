package lsieun.sample.java1_inner_classes;

public class D2 {
    public void test() {
        Book book = new Book("Design Patterns") {
            @Override
            public String description() {
                return "Famous GoF book.";
            }
        };
    }
}
