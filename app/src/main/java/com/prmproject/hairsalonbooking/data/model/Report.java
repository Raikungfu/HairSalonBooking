package com.prmproject.hairsalonbooking.data.model;

import com.prmproject.hairsalonbooking.data.model.type.ReportStatusEnum;

public class Report {

    private int reportId;
    private String reportName;
    private String reportLink;
    private Integer stylistId;
    private Integer bookingId;
    private ReportStatusEnum status;
    private String createDate;
    private String createBy;
    private String updateDate;
    private String updateBy;

    private Booking booking;
    private User stylist;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportLink() {
        return reportLink;
    }

    public void setReportLink(String reportLink) {
        this.reportLink = reportLink;
    }

    public Integer getStylistId() {
        return stylistId;
    }

    public void setStylistId(Integer stylistId) {
        this.stylistId = stylistId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public ReportStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ReportStatusEnum status) {
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

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getStylist() {
        return stylist;
    }

    public void setStylist(User stylist) {
        this.stylist = stylist;
    }
}
