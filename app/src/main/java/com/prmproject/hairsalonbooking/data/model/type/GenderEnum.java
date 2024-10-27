package com.prmproject.hairsalonbooking.data.model.type;

public enum GenderEnum {
    Unknown(0),
    Male(1),
    Female(2);

    private final int value;

    GenderEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
