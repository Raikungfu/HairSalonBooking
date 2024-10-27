package com.prmproject.hairsalonbooking.data.model;

public class ServicesStylist {

    private int serviceStylistId;
    private Integer stylistId;
    private Integer serviceId;
    private String createDate;
    private String createBy;
    private String updateDate;
    private String updateBy;

    private HairService service;
    private User stylist;

    public int getServiceStylistId() {
        return serviceStylistId;
    }

    public void setServiceStylistId(int serviceStylistId) {
        this.serviceStylistId = serviceStylistId;
    }

    public Integer getStylistId() {
        return stylistId;
    }

    public void setStylistId(Integer stylistId) {
        this.stylistId = stylistId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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

    public HairService getService() {
        return service;
    }

    public void setService(HairService service) {
        this.service = service;
    }

    public User getStylist() {
        return stylist;
    }

    public void setStylist(User stylist) {
        this.stylist = stylist;
    }
}
