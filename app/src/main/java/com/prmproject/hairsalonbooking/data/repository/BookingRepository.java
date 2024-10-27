package com.prmproject.hairsalonbooking.data.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prmproject.hairsalonbooking.data.model.Booking;
import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.UserProfile;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.remote.BookingApiService;
import com.prmproject.hairsalonbooking.data.repository.Generic.ResponseListData;
import com.prmproject.hairsalonbooking.data.repository.Generic.ResponseObjectData;
import com.prmproject.hairsalonbooking.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingRepository {
    private BookingApiService apiService;
    private ResponseObjectData<Booking> responseData;
    private ResponseListData<RequestDTO.BookingHistoryDTO> responseListHistoryBookingData;
    private ResponseListData<Booking> responseListBookingData;

    public BookingRepository() {
        apiService = RetrofitClient.getInstance().create(BookingApiService.class);
        responseData = new ResponseObjectData<>();
        responseListHistoryBookingData = new ResponseListData<>();
        responseListBookingData = new ResponseListData<>();
    }


    public LiveData<ObjectResponse> createBooking(RequestDTO.BookingRequestDTO model) {
        MutableLiveData<ObjectResponse> responseLiveData = new MutableLiveData<>();

        apiService.createBooking(model).enqueue(new Callback<ObjectResponse<Booking>>() {
            @Override
            public void onResponse(Call<ObjectResponse<Booking>> call, Response<ObjectResponse<Booking>> response) {
                responseLiveData.postValue(responseData.getObjectData(response).getValue());
            }

            @Override
            public void onFailure(Call<ObjectResponse<Booking>> call, Throwable t) {
                responseLiveData.setValue(new ObjectResponse(null, t.getMessage(), false));
            }
        });

        return responseLiveData;
    }

    public LiveData<ListResponse> getBookingHistory() {
        MutableLiveData<ListResponse> responseListLiveData = new MutableLiveData<>();

        apiService.getBookingHistory().enqueue(new Callback<ListResponse<RequestDTO.BookingHistoryDTO>>() {
            @Override
            public void onResponse(Call<ListResponse<RequestDTO.BookingHistoryDTO>> call, Response<ListResponse<RequestDTO.BookingHistoryDTO>> response) {
                responseListLiveData.postValue(responseListHistoryBookingData.getListData(response).getValue());
            }

            @Override
            public void onFailure(Call<ListResponse<RequestDTO.BookingHistoryDTO>> call, Throwable t) {
                responseListLiveData.setValue(new ListResponse<>(null, t.getMessage(), false));
            }
        });

        return responseListLiveData;
    }

    public LiveData<ListResponse> getListBooking(int page, int pageSize) {
        MutableLiveData<ListResponse> responseListLiveData = new MutableLiveData<>();

        apiService.getAllBookings(page, pageSize).enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                responseListLiveData.postValue(new ListResponse<>(response.body(), "Lấy danh sách booking thành công", true));            }
            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                responseListLiveData.setValue(new ListResponse<>(null, t.getMessage(), false));
            }
        });

        return responseListLiveData;
    }

    public LiveData<ObjectResponse> changeBookingStatus(int id, RequestDTO.ChangeBookingStatusDTO model) {
        MutableLiveData<ObjectResponse> responseLiveData = new MutableLiveData<>();

        apiService.changeBookingStatus(id, model).enqueue(new Callback<ObjectResponse<String>>() {
            @Override
            public void onResponse(Call<ObjectResponse<String>> call, Response<ObjectResponse<String>> response) {
                responseLiveData.postValue(new ObjectResponse(response.body(), "Change booking status success", true));
            }

            @Override
            public void onFailure(Call<ObjectResponse<String>> call, Throwable t) {
                responseLiveData.setValue(new ObjectResponse(null, t.getMessage(), false));
            }
        });

        return responseLiveData;
    }

}
