package com.prmproject.hairsalonbooking.data.model.type;

public enum ScheduleEnum {
    None(0),
    Available(1),
    UnAvailable(2);

    private final int value;

    ScheduleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

