package com.prmproject.hairsalonbooking.data.repository.PaypalPayment;

import org.json.JSONObject;

public interface OrderCaptureListener {
    void onSuccess(JSONObject response);
    void onError(String error);
}