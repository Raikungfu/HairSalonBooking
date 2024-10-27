package com.prmproject.hairsalonbooking.data.model.dataToReceive;

import java.util.List;
import java.util.Map;

public class MessageResponse<T> {
        private String message;
        private int status;
        private boolean statusCode;
        private T data;

        public MessageResponse() {
        }

        public MessageResponse(String message, boolean statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }

    public MessageResponse(String message, int status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public MessageResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isStatusCode() {
        return statusCode;
    }

    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }
}
