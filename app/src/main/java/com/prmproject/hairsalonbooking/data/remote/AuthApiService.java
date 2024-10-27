package com.prmproject.hairsalonbooking.data.remote;


import com.prmproject.hairsalonbooking.data.model.dataToReceive.MessageResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.TokenResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.UserLogin;
import com.prmproject.hairsalonbooking.data.model.dataToSend.UserRegister;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthApiService {
    @POST("api/v1/users/login")
    Call<ResponseBody> login(@Body UserLogin user);

    @POST("api/v1/users/register")
    Call<MessageResponse<UserRegister>> register(@Body UserRegister user);
}
