package com.prmproject.hairsalonbooking;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.prmproject.hairsalonbooking.network.RetrofitClient;

public class HairSalonBooking extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN_KEY", null);
        if (token != null) {
            RetrofitClient.updateToken(token);
        }
        RetrofitClient.getInstance();
    }
}