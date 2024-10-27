package com.prmproject.hairsalonbooking.data.model;

import com.prmproject.hairsalonbooking.data.model.type.ScheduleEnum;

import java.util.Set;
import java.util.HashSet;

public class Schedule {

    private int scheduleId;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    private ScheduleEnum status;
    private String createDate;
    private String createBy;
    private String updateDate;
    private String updateBy;

    private Set<Booking> bookings = new HashSet<>();
    private Set<ScheduleUser> scheduleUsers = new HashSet<>();

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ScheduleEnum getStatus() {
        return status;
    }

    public void setStatus(ScheduleEnum status) {
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

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<ScheduleUser> getScheduleUsers() {
        return scheduleUsers;
    }

    public void setScheduleUsers(Set<ScheduleUser> scheduleUsers) {
        this.scheduleUsers = scheduleUsers;
    }
}
