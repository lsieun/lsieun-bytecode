package lsieun.sample.java1_inner_classes;

public abstract class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public abstract String description();
}
