package com.prmproject.hairsalonbooking.data.model;

import java.util.List;
import java.util.Map;
public class BookingInfo {
    private int stylistId;
    private int getServiceId;
    private String serviceName;
    private String stylistName;
    private double price; // Hoặc kiểu dữ liệu bạn đang sử dụng cho giá
    private String estimatedTime; // Thời gian ước tính

    public BookingInfo(int stylistId, String serviceName, String stylistName, double price, String estimatedTime, int getServiceId) {
        this.stylistId = stylistId;
        this.serviceName = serviceName;
        this.stylistName = stylistName;
        this.price = price;
        this.estimatedTime = estimatedTime;
        this.getServiceId = getServiceId;
    }

    public int getStylistId() {
        return stylistId;
    }

    public void setStylistId(int stylistId) {
        this.stylistId = stylistId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStylistName() {
        return stylistName;
    }

    public void setStylistName(String stylistName) {
        this.stylistName = stylistName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getGetServiceId() {
        return getServiceId;
    }

    public void setGetServiceId(int getServiceId) {
        this.getServiceId = getServiceId;
    }
}
