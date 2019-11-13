package lsieun.sample.classfile_attr;

import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Attr_BootstrapMethods {
    public Consumer<String> getConsumer() {
        return System.out::println;
    }

    public Function<BigInteger, String> getFunction() {
        return BigInteger::toString;
    }

    public Supplier<String> getSupplier1() {
        return () -> "Hello World!";
    }

    public Supplier<String> getSupplier2() {
        return () -> "Hello Lambda!";
    }
}
