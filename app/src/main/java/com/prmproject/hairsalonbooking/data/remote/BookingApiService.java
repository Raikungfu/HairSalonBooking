package com.prmproject.hairsalonbooking.data.remote;

import com.prmproject.hairsalonbooking.data.model.Booking;
import com.prmproject.hairsalonbooking.data.model.BookingDetail;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookingApiService {

    @POST("api/v1/booking/createBooking")
    Call<ObjectResponse<Booking>> createBooking(@Body RequestDTO.BookingRequestDTO request);

    @GET("api/v1/booking/history")
    Call<ListResponse<RequestDTO.BookingHistoryDTO>> getBookingHistory();

    @POST("api/v1/booking/changeBookingStatus/{id}")
    Call<ObjectResponse<String>> changeBookingStatus(@Path("id") int id, @Body RequestDTO.ChangeBookingStatusDTO request);

    @GET("api/v1/booking")
    Call<List<Booking>> getAllBookings(@Query("page") int page, @Query("pageSize") int pageSize);
}
