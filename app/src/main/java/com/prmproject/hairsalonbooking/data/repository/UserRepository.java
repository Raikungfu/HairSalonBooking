package com.prmproject.hairsalonbooking.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prmproject.hairsalonbooking.data.model.Booking;
import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.User;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.remote.BookingApiService;
import com.prmproject.hairsalonbooking.data.remote.UserApiService;
import com.prmproject.hairsalonbooking.data.repository.Generic.ResponseListData;
import com.prmproject.hairsalonbooking.data.repository.Generic.ResponseObjectData;
import com.prmproject.hairsalonbooking.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
        private UserApiService apiService;
        private ResponseObjectData<User> responseData;
    private ResponseObjectData<String> responseStringData;
    private ResponseListData<User> responseListData;

        public UserRepository() {
            apiService = RetrofitClient.getInstance().create(UserApiService.class);
            responseData = new ResponseObjectData<>();
            responseListData = new ResponseListData<>();
            responseStringData = new ResponseObjectData<>();
        }

    public LiveData<ObjectResponse> createUser(RequestDTO.CreateAccountDTO model) {
        MutableLiveData<ObjectResponse> responseLiveData = new MutableLiveData<>();

        apiService.createAccount(model).enqueue(new Callback<ObjectResponse<String>>() {
            @Override
            public void onResponse(Call<ObjectResponse<String>> call, Response<ObjectResponse<String>> response) {
                responseLiveData.postValue(responseStringData.getObjectData(response).getValue());
            }

            @Override
            public void onFailure(Call<ObjectResponse<String>> call, Throwable t) {
                responseLiveData.setValue(new ObjectResponse(null, t.getMessage(), false));
            }
        });

        return responseLiveData;
    }

    public LiveData<ObjectResponse> updateUser(int id, RequestDTO.UpdateAccountDTO model) {
        MutableLiveData<ObjectResponse> responseLiveData = new MutableLiveData<>();

        apiService.updateAccount(id, model).enqueue(new Callback<ObjectResponse<String>>() {
            @Override
            public void onResponse(Call<ObjectResponse<String>> call, Response<ObjectResponse<String>> response) {
                responseLiveData.postValue(responseStringData.getObjectData(response).getValue());
            }

            @Override
            public void onFailure(Call<ObjectResponse<String>> call, Throwable t) {
                responseLiveData.setValue(new ObjectResponse(null, t.getMessage(), false));
            }
        });

        return responseLiveData;
    }

    public LiveData<ListResponse> getListUser() {
        MutableLiveData<ListResponse> responseListLiveData = new MutableLiveData<>();

        apiService.getListUser().enqueue(new Callback<ListResponse<User>>() {
            @Override
            public void onResponse(Call<ListResponse<User>> call, Response<ListResponse<User>> response) {
                responseListLiveData.postValue(responseListData.getListData(response).getValue());
            }

            @Override
            public void onFailure(Call<ListResponse<User>> call, Throwable t) {
                responseListLiveData.setValue(new ListResponse<>(null, t.getMessage(), false));
            }
        });

        return responseListLiveData;
    }

    public LiveData<ObjectResponse> changeStatusAccount(int id) {
        MutableLiveData<ObjectResponse> responseLiveData = new MutableLiveData<>();

        apiService.changeStatusAccount(id).enqueue(new Callback<ObjectResponse<String>>() {
            @Override
            public void onResponse(Call<ObjectResponse<String>> call, Response<ObjectResponse<String>> response) {
                responseLiveData.postValue(responseStringData.getObjectData(response).getValue());
            }

            @Override
            public void onFailure(Call<ObjectResponse<String>> call, Throwable t) {
                responseLiveData.setValue(new ObjectResponse(null, t.getMessage(), false));
            }
        });

        return responseLiveData;
    }
}
