package lsieun.sample.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MultiCatch {
    public void test() {
        try {
            InputStream in = new FileInputStream("/path/to/file");
            in.close();
        }
        catch (FileNotFoundException e1) {
            System.out.println("e1: " + e1.getMessage());
        }
        catch (IOException e2) {
            System.out.println("e2: " + e2.getMessage());
        }

    }
}
