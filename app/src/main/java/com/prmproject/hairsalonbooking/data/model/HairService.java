package com.prmproject.hairsalonbooking.data.model;

import java.util.HashSet;
import java.util.Set;

public class HairService {

    private int serviceId;
    private String imageLink;
    private String serviceName;
    private String description;
    private Double price;
    private String estimateTime;
    private Integer status;
    private String createDate;
    private String createBy;
    private String updateDate;
    private String updateBy;
    private boolean isSelected;

    private Set<BookingDetail> bookingDetails = new HashSet<>();
    private Set<ServicesStylist> servicesStylists = new HashSet<>();

    public HairService() {
        bookingDetails = new HashSet<>();
        servicesStylists = new HashSet<>();
    }

    public HairService(int serviceId, String imageLink, String serviceName, String description, Double price, String estimateTime) {
        this.serviceId = serviceId;
        this.imageLink = imageLink;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.estimateTime = estimateTime;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(String estimateTime) {
        this.estimateTime = estimateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Set<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(Set<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public Set<ServicesStylist> getServicesStylists() {
        return servicesStylists;
    }

    public void setServicesStylists(Set<ServicesStylist> servicesStylists) {
        this.servicesStylists = servicesStylists;
    }
}
