package lsieun.sample.java7_try_with;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class A {
    public void readFile(final File file) {
        try (InputStream in = new FileInputStream(file)) {
            // Some implementation here
            int b = in.read();
            System.out.println(b);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
