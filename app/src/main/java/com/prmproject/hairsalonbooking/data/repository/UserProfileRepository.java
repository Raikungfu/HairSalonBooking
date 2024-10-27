package com.prmproject.hairsalonbooking.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.User;
import com.prmproject.hairsalonbooking.data.model.UserProfile;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.type.Stylist;
import com.prmproject.hairsalonbooking.data.remote.UserApiService;
import com.prmproject.hairsalonbooking.data.remote.UserProfileApiService;
import com.prmproject.hairsalonbooking.data.repository.Generic.ResponseListData;
import com.prmproject.hairsalonbooking.data.repository.Generic.ResponseObjectData;
import com.prmproject.hairsalonbooking.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileRepository {
    private UserProfileApiService apiService;
    private UserApiService apiUserService;
    private ResponseObjectData<UserProfile> responseData;
    private ResponseObjectData<User> responseUserData;

    public UserProfileRepository() {
        apiService = RetrofitClient.getInstance().create(UserProfileApiService.class);
        apiUserService = RetrofitClient.getInstance().create(UserApiService.class);
        responseData = new ResponseObjectData<>();
        responseUserData = new ResponseObjectData<>();
    }

    public LiveData<ObjectResponse> getCurrentUserProfile() {
        MutableLiveData<ObjectResponse> responseLiveData = new MutableLiveData<>();

        apiService.getCurrentUserProfile().enqueue(new Callback<ObjectResponse<UserProfile>>() {
            @Override
            public void onResponse(Call<ObjectResponse<UserProfile>> call, Response<ObjectResponse<UserProfile>> response) {
                responseLiveData.postValue(responseData.getObjectData(response).getValue());
            }

            @Override
            public void onFailure(Call<ObjectResponse<UserProfile>> call, Throwable t) {
                responseLiveData.setValue(new ObjectResponse(null, t.getMessage(), false));
            }
        });

        return responseLiveData;
    }

    public LiveData<ObjectResponse> getCurrentUser() {
        MutableLiveData<ObjectResponse> responseLiveData = new MutableLiveData<>();

        apiUserService.getCurrentUser().enqueue(new Callback<ObjectResponse<User>>() {
            @Override
            public void onResponse(Call<ObjectResponse<User>> call, Response<ObjectResponse<User>> response) {
                responseLiveData.postValue(responseUserData.getObjectData(response).getValue());
            }

            @Override
            public void onFailure(Call<ObjectResponse<User>> call, Throwable t) {
                responseLiveData.setValue(new ObjectResponse(null, t.getMessage(), false));
            }
        });

        return responseLiveData;
    }
}
