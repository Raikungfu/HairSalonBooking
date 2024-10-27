package com.prmproject.hairsalonbooking.data.model.type;

public enum BookingStatus {
    None(0),
    InQueue(1),
    Accepted(2),
    InProgress(3),
    Delay(4),
    Complete(5),
    Cancel(6);

    private final int value;

    BookingStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

