package lsieun.sample;

import java.lang.reflect.Method;

public class Wrapper<T> {
    private T wrapped;

    public Wrapper(T arg) {
        wrapped = arg;
    }

    public Wrapper<T> clone() {
        Wrapper<T> clon = null;
        try {
            clon = (Wrapper<T>) super.clone(); // unchecked warning
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        try {
            Class<?> clazz = this.wrapped.getClass();
            Method meth = clazz.getMethod("clone", new Class[0]);
            Object dupl = meth.invoke(this.wrapped, new Object[0]);
            clon.wrapped = (T) dupl; // unchecked warning
        } catch (Exception e) {
        }
        return clon;
    }
}
