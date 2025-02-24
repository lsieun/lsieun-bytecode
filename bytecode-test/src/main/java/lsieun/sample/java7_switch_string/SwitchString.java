package lsieun.sample.java7_switch_string;

public class SwitchString {
    public void test(String input) {
        String result;

        switch (input) {
            case "AAA":
                result = input + "A";
                break;
            case "BBB":
                result = input + "B";
                break;
            default:
                result = input + "C";
        }

        System.out.println(result);
    }
}
