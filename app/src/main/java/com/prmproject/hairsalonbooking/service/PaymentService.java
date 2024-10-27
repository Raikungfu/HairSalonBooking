package com.prmproject.hairsalonbooking.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.prmproject.hairsalonbooking.PaymentWebViewActivity;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.remote.PaymentApiService;
import com.prmproject.hairsalonbooking.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
public class PaymentService {
    private static final int REQUEST_CODE_PAYMENT = 222;
    private Context context;
    private PaymentApiService apiService;

    public PaymentService(Context context) {
        this.context = context;
        apiService = RetrofitClient.getInstance().create(PaymentApiService.class);
    }


    public void createPayment(RequestDTO.VnPaymentRequestModel model) {
        Call<RequestDTO.CheckoutResponse> call = apiService.createPaymentUrl(model);

        call.enqueue(new Callback<RequestDTO.CheckoutResponse>() {
            @Override
            public void onResponse(Call<RequestDTO.CheckoutResponse> call, retrofit2.Response<RequestDTO.CheckoutResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String paymentUrl = response.body().getPaymentUrl();

                    Intent intent = new Intent(context, PaymentWebViewActivity.class);
                    intent.putExtra("paymentUrl", paymentUrl);
                    ((Activity) context).startActivityForResult(intent, REQUEST_CODE_PAYMENT);
                } else {
                    Toast.makeText(context, "Lỗi khi nhận URL thanh toán", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestDTO.CheckoutResponse> call, Throwable t) {
                Toast.makeText(context, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
