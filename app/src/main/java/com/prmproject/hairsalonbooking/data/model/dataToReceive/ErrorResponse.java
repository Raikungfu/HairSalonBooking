package com.prmproject.hairsalonbooking.data.model.dataToReceive;

import android.text.TextUtils;

import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private int status;
    private String message;
    private Object data;

    // Các getter và setter
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String convertErrorToString() {
        return "Status: " + status + ", Message: " + message;
    }
}
