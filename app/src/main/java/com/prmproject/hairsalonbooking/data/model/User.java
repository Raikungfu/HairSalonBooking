package com.prmproject.hairsalonbooking.data.model;

import com.prmproject.hairsalonbooking.data.model.type.BookingStatus;
import com.prmproject.hairsalonbooking.data.model.type.UserRole;
import com.prmproject.hairsalonbooking.data.model.type.UserStatus;

import java.util.List;

public class User {

    private int userId;
    private String userName;
    private String password;
    private String phone;
    private Integer status;
    private Integer role;
    private String createDate;
    private String createBy;
    private String updateDate;
    private String updateBy;

    private UserProfile userProfile;
    private List<Booking> bookingCustomers;
    private List<BookingDetail> bookingDetails;
    private List<Booking> bookingManagers;
    private List<Booking> bookingStaffs;
    private List<Feedback> feedbacks;
    private List<Report> reports;
    private List<ScheduleUser> scheduleUsers;
    private List<ServicesStylist> servicesStylists;

    public User(int userId, String userName, String password, String phone, Integer status, Integer role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserStatus getStatus() {
        return status != null ? UserStatus.values()[status] : null;
    }

    public Integer getStatusInt() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role != null ? UserRole.values()[role] : null;
    }

    public Integer getRoleInt() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<Booking> getBookingCustomers() {
        return bookingCustomers;
    }

    public void setBookingCustomers(List<Booking> bookingCustomers) {
        this.bookingCustomers = bookingCustomers;
    }

    public List<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public List<Booking> getBookingManagers() {
        return bookingManagers;
    }

    public void setBookingManagers(List<Booking> bookingManagers) {
        this.bookingManagers = bookingManagers;
    }

    public List<Booking> getBookingStaffs() {
        return bookingStaffs;
    }

    public void setBookingStaffs(List<Booking> bookingStaffs) {
        this.bookingStaffs = bookingStaffs;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<ScheduleUser> getScheduleUsers() {
        return scheduleUsers;
    }

    public void setScheduleUsers(List<ScheduleUser> scheduleUsers) {
        this.scheduleUsers = scheduleUsers;
    }

    public List<ServicesStylist> getServicesStylists() {
        return servicesStylists;
    }

    public void setServicesStylists(List<ServicesStylist> servicesStylists) {
        this.servicesStylists = servicesStylists;
    }
}
