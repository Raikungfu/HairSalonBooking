package com.prmproject.hairsalonbooking.data.model.dataToSend;

import com.prmproject.hairsalonbooking.data.model.type.BookingStatus;
import com.prmproject.hairsalonbooking.data.model.type.GenderEnum;
import com.prmproject.hairsalonbooking.data.model.type.ReportStatusEnum;
import com.prmproject.hairsalonbooking.data.model.type.UserRole;
import com.prmproject.hairsalonbooking.data.model.type.VnPayMethod;
import com.prmproject.hairsalonbooking.data.model.type.UserStatus;

import javax.validation.constraints.*;
import java.util.List;

import retrofit2.http.Url;


public class RequestDTO {

    public static class CheckoutResponse {
        private String paymentUrl;

        public String getPaymentUrl() {
            return paymentUrl;
        }
    }


    public static class LoginRequestDTO {
        private String userName;
        private String password;

        // Getters and Setters
    }

    public static class RegisterRequestDTO {
        @NotNull
        @Size(max = 255, message = "Username cannot exceed 255 characters.")
        private String userName;

        @Size(max = 255, message = "Password cannot exceed 255 characters.")
        private String password;

        @NotNull
        @Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be between 10 and 15 digits and only contain numbers.")
        private String phone;

        // Getters and Setters
    }

    public static class BookingRequestDTO {
        private String userName;
        private String phone;
        private Integer voucherId;
        private String password;

        @NotNull
        private Integer scheduleId;

        @NotNull
        private List<Integer> serviceId;

        @NotNull
        private List<Integer> stylistId;

        public BookingRequestDTO(String userName, String phone, Integer voucherId, String password, Integer scheduleId, List<Integer> serviceId, List<Integer> stylistId) {
            this.userName = userName;
            this.phone = phone;
            this.voucherId = voucherId;
            this.password = password;
            this.scheduleId = scheduleId;
            this.serviceId = serviceId;
            this.stylistId = stylistId;
        }
    }

    public static class CheckoutRequestDTO {
        private Double totalPrice; // Tổng giá của booking
        private String createDate; // Ngày tạo booking
        private String description; // Mô tả booking
        private String fullName; // Tên khách hàng
        private Integer bookingId; // ID của booking

        // Getters and Setters
    }

    public static class UpdateUserProfileDTO {
        @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format.")
        private String imageLink;

        @Size(max = 100, message = "Full Name cannot be longer than 100 characters.")
        private String fullName;

        @Email(message = "Invalid email format.")
        private String email;

        private GenderEnum gender;

        @Size(max = 200, message = "Address cannot be longer than 200 characters.")
        private String address;

        private String dateOfBirth;

        @Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be between 10 and 15 digits and only contain numbers.")
        private String phone;

        // Getters and Setters
    }

    public static class ChangeStatusAccountDTO {
        private UserStatus status;

        // Getters and Setters
    }

    public static class CreateReportDTO {
        private Integer bookingId;

        @Size(max = 100, message = "Full Name cannot be longer than 100 characters.")
        private String reportName;

        @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format.")
        private String reportLink;

        // Getters and Setters
    }

    public static class UpdateReportDTO {
        @Size(max = 100, message = "Full Name cannot be longer than 100 characters.")
        private String reportName;

        @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format.")
        private String reportLink;

        // Getters and Setters
    }

    public static class RemoveReportDTO {
        private ReportStatusEnum status;

        // Getters and Setters
    }

    public static class ChangeBookingStatusDTO {
        private BookingStatus status;

        public ChangeBookingStatusDTO(BookingStatus status) {
            this.status = status;
        }
    }

    public static class SearchAccountByNameDTO {
        @Size(max = 100, message = "Full Name cannot be longer than 100 characters.")
        private String userName;

        // Getters and Setters
    }

    public static class CreateServiceDTO {
        @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format.")
        private String imageLink;

        @Size(max = 100, message = "Service Name cannot be longer than 100 characters.")
        private String serviceName;

        private String description;
        private Integer price;
        private String estimateTime; // Change this to appropriate type if needed

        public CreateServiceDTO(String imageLink, String serviceName, String description, Integer price, String estimateTime) {
            this.imageLink = imageLink;
            this.serviceName = serviceName;
            this.description = description;
            this.price = price;
            this.estimateTime = estimateTime;
        }

        // Getters and Setters
    }

    public static class UpdateServiceDTO {
        @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format.")
        private String imageLink;

        @Size(max = 100, message = "Service Name cannot be longer than 100 characters.")
        private String serviceName;

        private String description;
        private Integer price;
        private String estimateTime; // Change this to appropriate type if needed

        public UpdateServiceDTO(String imageLink, String serviceName, String description, Integer price, String estimateTime) {
            this.imageLink = imageLink;
            this.serviceName = serviceName;
            this.description = description;
            this.price = price;
            this.estimateTime = estimateTime;
        }

        // Getters and Setters
    }

    public static class RemoveServiceDTO {
        private Integer status;

        public RemoveServiceDTO(Integer status) {
            this.status = status;
        }

        // Getters and Setters
    }

    public static class CreateScheduleDTO {
        private String startTime; // Change this to appropriate type if needed
        private String endTime; // Change this to appropriate type if needed
        private String startDate; // Change this to appropriate type if needed
        private String endDate; // Change this to appropriate type if needed

        // Getters and Setters
    }

    public static class UpdateScheduleDTO {
        private String startTime; // Change this to appropriate type if needed
        private String endTime; // Change this to appropriate type if needed
        private String startDate; // Change this to appropriate type if needed
        private String endDate; // Change this to appropriate type if needed

        // Getters and Setters
    }

    public static class RemoveScheduleDTO {
        private Integer status;

        // Getters and Setters
    }

    public static class CreateAccountDTO {
        @NotNull
        @Size(max = 255, message = "Username cannot exceed 255 characters.")
        private String userName;

        @Size(max = 255, message = "Password cannot exceed 255 characters.")
        private String password;

        @NotNull
        @Size(min = 8, max = 20, message = "Phone must be 8 to 20 characters long.")
        @Pattern(regexp = "^\\d+$", message = "Phone must be number only.")
        private String phone;

        @NotNull
        private UserRole roleId;

        public CreateAccountDTO(String userName, String password, String phone, UserRole roleId) {
            this.userName = userName;
            this.password = password;
            this.phone = phone;
            this.roleId = roleId;
        }
        // Getters and Setters
    }

    public static class UpdateAccountDTO {
        @NotNull
        @Size(max = 255, message = "Username cannot exceed 255 characters.")
        private String userName;

        @Size(max = 255, message = "Password cannot exceed 255 characters.")
        private String password;

        @NotNull
        @Size(min = 8, max = 20, message = "Phone must be 8 to 20 characters long.")
        @Pattern(regexp = "^\\d+$", message = "Phone must be number only.")
        private String phone;

        @NotNull
        private UserRole roleId;

        public UpdateAccountDTO(String userName, String password, String phone, UserRole roleId) {
            this.userName = userName;
            this.password = password;
            this.phone = phone;
            this.roleId = roleId;
        }

        // Getters and Setters
    }

    public static class BookingHistoryDTO {
        private Integer serviceId;
        private String serviceName;
        private Integer userId;
        private String username;
        private Integer scheduleId;
        private String startDate; // Change this to appropriate type if needed
        private String endDate; // Change this to appropriate type if needed
        private Double totalPrice;

        public Integer getServiceId() {
            return serviceId;
        }

        public void setServiceId(Integer serviceId) {
            this.serviceId = serviceId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Integer getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(Integer scheduleId) {
            this.scheduleId = scheduleId;
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

        public Double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
        }

        // Getters and Setters
    }

    public static class VnPaymentRequestModel {
        private Integer bookingId;
        private String fullName;
        private String description;
        private Double totalPrice;
        private String createDate;
        private VnPayMethod VnPayMethod;

        public VnPaymentRequestModel(Integer bookingId, Double totalPrice, VnPayMethod vnPayMethod) {
            this.bookingId = bookingId;
            this.totalPrice = totalPrice;
            this.VnPayMethod = vnPayMethod;
        }

        // Getters and Setters
    }

    public static class UpdateVoucherDTO {
        @Positive(message = "Discount amount must be a positive value.")
        private Double discountAmount;

        @Future(message = "Start date must not be in the past.")
        private String startDate; // Change this to appropriate type if needed

        @Future(message = "End date must not be in the past.")
        @EndDateValidation(startDate = "startDate", message = "End date must be greater than start date.")
        private String endDate; // Change this to appropriate type if needed

        // Getters and Setters
    }

    public static class CreateVoucherDTO {
        @Positive(message = "Discount amount must be a positive value.")
        private Double discountAmount;

        @Future(message = "Start date must not be in the past.")
        private String startDate; // Change this to appropriate type if needed

        @Future(message = "End date must not be in the past.")
        @EndDateValidation(startDate = "startDate", message = "End date must be greater than start date.")
        private String endDate; // Change this to appropriate type if needed

        // Getters and Setters
    }
}

