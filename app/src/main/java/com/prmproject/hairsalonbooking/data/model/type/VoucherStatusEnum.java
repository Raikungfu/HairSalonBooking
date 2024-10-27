package com.prmproject.hairsalonbooking.data.model.type;

public enum VoucherStatusEnum {
    NONE(0),
    ACTIVE(1),
    INACTIVE(2);

    private final int value;

    VoucherStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
