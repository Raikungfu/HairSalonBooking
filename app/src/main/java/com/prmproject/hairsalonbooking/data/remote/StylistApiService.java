package com.prmproject.hairsalonbooking.data.remote;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.type.Stylist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface StylistApiService {
    @GET("api/v1/servicesStylist/getStylistsByServiceIdAsync/{serviceId}")
    Call<ListResponse<Stylist>> getStylistsByServiceId(@Path("serviceId") int serviceId);
}
