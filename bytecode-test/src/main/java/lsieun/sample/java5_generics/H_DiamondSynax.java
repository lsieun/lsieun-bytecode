package lsieun.sample.java5_generics;

import java.util.ArrayList;
import java.util.List;

public class H_DiamondSynax {
    public void test() {
//        List<String> list = new ArrayList<>();
//        String str = list.get(0);
        List list = new ArrayList<String>();
        list.add(new Object());
        System.out.println(list.get(0));
    }
}
