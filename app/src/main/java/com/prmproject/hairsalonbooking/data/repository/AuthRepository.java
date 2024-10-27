package com.prmproject.hairsalonbooking.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ErrorResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.MessageResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.TokenResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.UserLogin;
import com.prmproject.hairsalonbooking.data.model.dataToSend.UserRegister;
import com.prmproject.hairsalonbooking.data.remote.AuthApiService;
import com.prmproject.hairsalonbooking.network.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private AuthApiService apiService;

    public AuthRepository() {
        apiService = RetrofitClient.getInstance().create(AuthApiService.class);
    }
    public LiveData<TokenResponse> login(UserLogin user) {
        MutableLiveData<TokenResponse> tokenLiveData = new MutableLiveData<>();
        apiService.login(user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = null;
                    try {
                        responseString = response.body().string();
                        Log.d("Token", responseString);
                        tokenLiveData.postValue(new TokenResponse(responseString, "", true));
                        RetrofitClient.updateToken(responseString);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    Log.e("Token", "Response Error: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                    tokenLiveData.setValue(new TokenResponse("", "Invalid response", false));
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Token", t.getMessage());
                tokenLiveData.setValue(new TokenResponse("", t.getMessage(), false));
            }
        });
        return tokenLiveData;
    }


    public LiveData<MessageResponse<UserRegister>> register(UserRegister user) {
        MutableLiveData<MessageResponse<UserRegister>> messageLiveData = new MutableLiveData<>();
        apiService.register(user).enqueue(new Callback<MessageResponse<UserRegister>>() {
            @Override
            public void onResponse(Call<MessageResponse<UserRegister>> call, Response<MessageResponse<UserRegister>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    messageLiveData.postValue(response.body());
                } else {
                    messageLiveData.postValue(new MessageResponse<UserRegister>("Error: " + (response.body() != null ? response.body().getMessage() : response.message()), false));
                }
            }

            @Override
            public void onFailure(Call<MessageResponse<UserRegister>> call, Throwable t) {
                messageLiveData.postValue(new MessageResponse<UserRegister>(t.getMessage(), false));
            }
        });
        return messageLiveData;
    }

}
