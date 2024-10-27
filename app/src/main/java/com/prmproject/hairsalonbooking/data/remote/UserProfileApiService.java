package com.prmproject.hairsalonbooking.data.remote;

import com.prmproject.hairsalonbooking.data.model.User;
import com.prmproject.hairsalonbooking.data.model.UserProfile;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserProfileApiService {
    @GET("api/v1/userprofile/current")
    Call<ObjectResponse<UserProfile>> getCurrentUserProfile();
}
