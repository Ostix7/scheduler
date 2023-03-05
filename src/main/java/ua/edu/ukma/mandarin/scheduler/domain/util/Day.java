package ua.edu.ukma.mandarin.scheduler.domain.util;

public enum Day {
    MONDAY(1),
    TUESDAY(2),
    WENDSDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int value;

    Day(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
