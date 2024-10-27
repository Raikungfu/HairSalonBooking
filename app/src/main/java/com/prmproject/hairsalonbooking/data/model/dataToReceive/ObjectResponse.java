package com.prmproject.hairsalonbooking.data.model.dataToReceive;

import java.util.List;

public class ObjectResponse<T> {
    private T data;
    private String message;
    private boolean success;
    private int status;
    private ErrorResponse error;

    public ObjectResponse(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public ObjectResponse(T data, String message, boolean success, int status) {
        this.data = data;
        this.message = message;
        this.success = success;
        this.status = status;
    }

    public ObjectResponse(ErrorResponse error, String message) {
        this.error = error;
        this.message = message;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
