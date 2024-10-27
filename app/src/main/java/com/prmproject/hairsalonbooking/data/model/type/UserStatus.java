package com.prmproject.hairsalonbooking.data.model.type;

public enum UserStatus {
    None(0),
    Active(1),
    Banned(2);

    private final int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}