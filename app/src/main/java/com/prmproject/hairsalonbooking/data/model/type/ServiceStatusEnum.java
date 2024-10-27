package com.prmproject.hairsalonbooking.data.model.type;

public enum ServiceStatusEnum {
    None(0),
    Active(1),
    Inactive(2);

    private final int value;

    ServiceStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
