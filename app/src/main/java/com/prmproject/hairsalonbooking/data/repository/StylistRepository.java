package com.prmproject.hairsalonbooking.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.ServicesStylist;
import com.prmproject.hairsalonbooking.data.model.User;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.type.Stylist;
import com.prmproject.hairsalonbooking.data.remote.HairServiceApiService;
import com.prmproject.hairsalonbooking.data.remote.StylistApiService;
import com.prmproject.hairsalonbooking.data.repository.Generic.ResponseListData;
import com.prmproject.hairsalonbooking.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StylistRepository {

    private final StylistApiService apiService;
    private ResponseListData<Stylist> responseListData;

    public StylistRepository() {
        this.apiService = RetrofitClient.getInstance().create(StylistApiService.class);
        responseListData = new ResponseListData<>();
    }

    public LiveData<ListResponse> getListServices(int id) {
        MutableLiveData<ListResponse> responseListLiveData = new MutableLiveData<>();

        apiService.getStylistsByServiceId(id).enqueue(new Callback<ListResponse<Stylist>>() {
            @Override
            public void onResponse(Call<ListResponse<Stylist>> call, Response<ListResponse<Stylist>> response) {
                if (response.isSuccessful()) {
                    responseListLiveData.postValue(responseListData.getListData(response).getValue());
                } else {
                    String errorMessage = "Unknown error";
                    if (response.code() == 401) {
                        errorMessage = "Unauthorized access. Please check your credentials.";
                    } else if (response.code() == 404) {
                        errorMessage = "Service not found.";
                    } else if (response.code() == 500) {
                        errorMessage = "Server error. Please try again later.";
                    }
                    responseListLiveData.setValue(new ListResponse<>(null, errorMessage, false, response.code()));
                }
            }

            @Override
            public void onFailure(Call<ListResponse<Stylist>> call, Throwable t) {
                responseListLiveData.setValue(new ListResponse<>(null, t.getMessage(), false, 400));
            }
        });

        return responseListLiveData;
    }
}