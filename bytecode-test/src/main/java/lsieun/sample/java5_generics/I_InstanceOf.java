package lsieun.sample.java5_generics;

import java.util.HashSet;
import java.util.Set;

public class I_InstanceOf {
    public void test1(Object o) {
        if (o instanceof Set<?>) {
            Set m = (Set) o;
        }
    }

    public void test2(Object o) {
        if (o instanceof Set) {
            Set<?> m = (Set<?>) o;
        }
        Set<Integer> s = new HashSet();
    }
}
