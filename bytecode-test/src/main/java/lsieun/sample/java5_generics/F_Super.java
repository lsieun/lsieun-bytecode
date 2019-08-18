package lsieun.sample.java5_generics;

import java.util.Collection;

/**
 * In contrast to <b>extends</b>, the <b>super</b> keyword restricts the type parameter to be a superclass of some other class.
 */
public class F_Super {
    public void iterate(final Collection<? super Integer> objects) {
        // Some implementation here
    }
}
