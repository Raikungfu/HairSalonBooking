package com.prmproject.hairsalonbooking.data.repository.PaypalPayment;

public interface OrderIDListener {
    void onOrderCreated(String orderId);
    void onError(String error);
}
