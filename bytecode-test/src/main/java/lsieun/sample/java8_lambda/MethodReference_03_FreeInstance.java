package lsieun.sample.java8_lambda;

import java.math.BigInteger;
import java.util.function.Function;

public class MethodReference_03_FreeInstance {
    public Function<BigInteger, String> getFunction() {
        return BigInteger::toString;
    }
}
