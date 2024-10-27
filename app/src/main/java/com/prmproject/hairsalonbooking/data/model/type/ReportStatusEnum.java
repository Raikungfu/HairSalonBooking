package com.prmproject.hairsalonbooking.data.model.type;

public enum ReportStatusEnum {
    None(0),
    Active(1),
    Inactive(2);

    private final int value;

    ReportStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
