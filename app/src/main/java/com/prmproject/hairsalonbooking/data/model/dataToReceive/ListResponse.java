package com.prmproject.hairsalonbooking.data.model.dataToReceive;

import java.util.List;

public class ListResponse<T> {
    private List<T> data;
    private String message;
    private boolean success;
    private int status;
    private ErrorResponse error;

    public ListResponse(List<T> data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public ListResponse(List<T> items, String message, boolean success, int status) {
        this.data = items;
        this.message = message;
        this.success = success;
        this.status = status;
    }

    public ListResponse(ErrorResponse error, String message) {
        this.error = error;
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> items) {
        this.data = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

