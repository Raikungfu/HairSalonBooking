package com.prmproject.hairsalonbooking.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.remote.HairServiceApiService;
import com.prmproject.hairsalonbooking.data.repository.Generic.ResponseListData;
import com.prmproject.hairsalonbooking.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HairServiceRepository {

    private final HairServiceApiService apiService;
    private ResponseListData<HairService> responseListData;
    private ResponseListData<String> responseStringData;

    public HairServiceRepository() {
        this.apiService = RetrofitClient.getInstance().create(HairServiceApiService.class);
        responseListData = new ResponseListData<>();
        responseStringData = new ResponseListData<>();
    }

    public LiveData<ListResponse> getListServices() {
        MutableLiveData<ListResponse> responseListLiveData = new MutableLiveData<>();

        apiService.getListServices().enqueue(new Callback<ListResponse<HairService>>() {
            @Override
            public void onResponse(Call<ListResponse<HairService>> call, Response<ListResponse<HairService>> response) {
                responseListLiveData.postValue(responseListData.getListData(response).getValue());
            }

            @Override
            public void onFailure(Call<ListResponse<HairService>> call, Throwable t) {
                responseListLiveData.setValue(new ListResponse<>(null, t.getMessage(), false));
            }
        });

        return responseListLiveData;
    }

    public LiveData<ObjectResponse<HairService>> getServiceDetail(int id) {
        MutableLiveData<ObjectResponse<HairService>> responseObjectLiveData = new MutableLiveData<>();

        apiService.getServiceById(id).enqueue(new Callback<ObjectResponse<HairService>>() {
            @Override
            public void onResponse(Call<ObjectResponse<HairService>> call, Response<ObjectResponse<HairService>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responseObjectLiveData.postValue(response.body());
                } else {
                    responseObjectLiveData.setValue(new ObjectResponse<>(null, "Error: " + response.message(), false));
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse<HairService>> call, Throwable t) {
                responseObjectLiveData.setValue(new ObjectResponse<>(null, t.getMessage(), false));
            }
        });

        return responseObjectLiveData;
    }

    public LiveData<ObjectResponse> createHairService(RequestDTO.CreateServiceDTO request) {
        MutableLiveData<ObjectResponse> responseObjectLiveData = new MutableLiveData<>();

        apiService.createService(request).enqueue(new Callback<ObjectResponse<String>>() {
            @Override
            public void onResponse(Call<ObjectResponse<String>> call, Response<ObjectResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responseObjectLiveData.postValue(response.body());
                } else {
                    responseObjectLiveData.setValue(new ObjectResponse<>(null, "Error: " + response.message(), false));
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse<String>> call, Throwable t) {
                responseObjectLiveData.setValue(new ObjectResponse<>(null, t.getMessage(), false));
            }
        });

        return responseObjectLiveData;
    }

    public LiveData<ObjectResponse> updateHairService(int id, RequestDTO.UpdateServiceDTO request) {
        MutableLiveData<ObjectResponse> responseObjectLiveData = new MutableLiveData<>();

        apiService.updateService(id, request).enqueue(new Callback<ObjectResponse<String>>() {
            @Override
            public void onResponse(Call<ObjectResponse<String>> call, Response<ObjectResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responseObjectLiveData.postValue(response.body());
                } else {
                    responseObjectLiveData.setValue(new ObjectResponse<>(null, "Error: " + response.message(), false));
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse<String>> call, Throwable t) {
                responseObjectLiveData.setValue(new ObjectResponse<>(null, t.getMessage(), false));
            }
        });

        return responseObjectLiveData;
    }

    public LiveData<ObjectResponse> deleteHairService(int id, RequestDTO.RemoveServiceDTO request) {
        MutableLiveData<ObjectResponse> responseObjectLiveData = new MutableLiveData<>();

        apiService.removeService(id, request).enqueue(new Callback<ObjectResponse<String>>() {
            @Override
            public void onResponse(Call<ObjectResponse<String>> call, Response<ObjectResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responseObjectLiveData.postValue(response.body());
                } else {
                    responseObjectLiveData.setValue(new ObjectResponse<>(null, "Error: " + response.message(), false));
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse<String>> call, Throwable t) {
                responseObjectLiveData.setValue(new ObjectResponse<>(null, t.getMessage(), false));
            }
        });

        return responseObjectLiveData;
    }
}
