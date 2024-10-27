package com.prmproject.hairsalonbooking.data.model.type;

public enum UserRole {
    None(0),
    Customer(1),
    Stylist(2),
    Staff(3),
    Manager(4),
    Admin(5);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

