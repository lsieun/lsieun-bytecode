package lsieun.sample.java1_inner_classes;

public class C_NewEnclosing {
    void run() {
        class Local {

            void run() {
                // method implementation
            }
        }
        Local local = new Local();
        local.run();
    }
}
