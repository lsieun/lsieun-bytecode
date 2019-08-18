package lsieun.sample.java5_enum;

public enum DaysOfTheWeekFieldsInterfaces implements DayOfWeek {
    MONDAY(false),
    TUESDAY(false),
    WEDNESDAY(false),
    THURSDAY(false),
    FRIDAY(false),
    SATURDAY(true),
    SUNDAY(true);

    private final boolean isWeekend;

    private DaysOfTheWeekFieldsInterfaces(final boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    @Override
    public boolean isWeekend() {
        return isWeekend;
    }

}
