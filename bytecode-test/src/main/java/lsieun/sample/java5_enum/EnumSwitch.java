package lsieun.sample.java5_enum;

public class EnumSwitch {

    public void performAction(DaysOfTheWeek instance) {
        String str = "";
        switch(instance) {
            case MONDAY:
                str = "mon";
                break;
            case TUESDAY:
                str = "tue";
                break;
            case WEDNESDAY:
                str = "wed";
                break;
            case THURSDAY:
                str = "thu";
                break;
            case FRIDAY:
                str = "fri";
                break;
            case SATURDAY:
                str = "sat";
                break;
            case SUNDAY:
                str = "sun";
                break;
        }
        System.out.println(str);
    }

}
