package com.prmproject.hairsalonbooking.data.model.type;

public class Stylist {
    private String stylistName;
    private int stylistId;

    public Stylist(String stylistName, int stylistId) {
        this.stylistName = stylistName;
        this.stylistId = stylistId;
    }

    public String getStylistName() {
        return stylistName;
    }

    public void setStylistName(String stylistName) {
        this.stylistName = stylistName;
    }

    public int getStylistId() {
        return stylistId;
    }

    public void setStylistId(int stylistId) {
        this.stylistId = stylistId;
    }
}
