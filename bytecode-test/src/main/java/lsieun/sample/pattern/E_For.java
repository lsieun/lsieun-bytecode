package lsieun.sample.pattern;

public class E_For {
    public void test_while() {
        int i = 0;
        while (i < 10) {
            System.out.println("Hello World");
            i++;
        }
    }

    public void test_for() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello World");
        }
    }

    public void test_break() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) break;
            System.out.println("Hello World");
        }
    }

    public void test_continue() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) continue;
            System.out.println("Hello World");
        }
    }

    public static void test_label() {
        outer:
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
            inner:
            for (int j = 10; j > 0; j--) {
                if (i == j) {
                    System.out.println(i + "," + j);
                    break outer;
                } else {
                    System.out.println("-->>" + i + "," + j);
                    continue inner;
                }
            }
        }
    }


    public static void discovering_pythagorean_triples() {
        int MAX_NUM = 100;
        String format = "(%s,%s,%s): %s + %s = %s";
        int count = 0;
        OUTER:
        for (int i = 1; i < MAX_NUM; i++) {
            for (int j = i + 1; j < MAX_NUM; j++) {
                for (int k = j + 1; k < MAX_NUM; k++) {
                    int power_i = i * i;
                    int power_j = j * j;
                    int power_k = k * k;
                    if ((power_i + power_j) == (power_k)) {
                        String line = String.format(format, i, j, k, power_i, power_j, power_k);
                        System.out.println(line);
                        count++;
                        if (count == 5) break OUTER;
                    }
                }
            }
        }
        System.out.println("count = " + count);
    }

    public static void main(String[] args) {
        discovering_pythagorean_triples();
    }
}
