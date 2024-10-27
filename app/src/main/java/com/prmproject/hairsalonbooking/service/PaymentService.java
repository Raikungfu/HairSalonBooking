package com.prmproject.hairsalonbooking.service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prmproject.hairsalonbooking.data.model.dataToReceive.TokenResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.model.dataToSend.UserLogin;
import com.prmproject.hairsalonbooking.data.remote.AuthApiService;
import com.prmproject.hairsalonbooking.data.remote.PaymentApiService;
import com.prmproject.hairsalonbooking.network.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentService {
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
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(paymentUrl));
                    context.startActivity(browserIntent);
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
