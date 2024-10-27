package com.prmproject.hairsalonbooking.data.remote;

import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PaymentApiService {
    @POST("api/v1/payment/checkout")
    Call<RequestDTO.CheckoutResponse> createPaymentUrl(@Body RequestDTO.VnPaymentRequestModel model);
}
