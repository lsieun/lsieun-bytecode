package lsieun.sample.reflection;

public class Object_Create {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        String str_obj = create_object(String.class);

        //Integer int_obj = create_object(Integer.class); // error: NoSuchMethodException: java.lang.Integer.<init>()
    }

    public static <T> T create_object(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t = (T) clazz.newInstance();
        return t;
    }
}
