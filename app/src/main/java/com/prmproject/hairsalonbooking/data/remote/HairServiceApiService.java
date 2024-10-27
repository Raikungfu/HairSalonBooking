package com.prmproject.hairsalonbooking.data.remote;

import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HairServiceApiService {

    @GET("api/v1/hairservice/list")
    Call<ListResponse<HairService>> getListServices();

    @GET("api/v1/hairservice/getServices/{id}")
    Call<ObjectResponse<HairService>> getServiceById(@Path("id") int id);

    @GET("api/v1/hairservice/{serviceName}")
    Call<ObjectResponse<HairService>> getServiceByName(@Path("serviceName") String serviceName);

    @POST("api/v1/hairservice/create")
    Call<ObjectResponse<String>> createService(@Body RequestDTO.CreateServiceDTO request);

    @POST("api/v1/hairservice/update/{serviceId}")
    Call<ObjectResponse<String>> updateService(@Path("serviceId") int serviceId, @Body RequestDTO.UpdateServiceDTO request);

    @POST("api/v1/hairservice/remove/{serviceId}")
    Call<ObjectResponse<String>> removeService(@Path("serviceId") int serviceId, @Body RequestDTO.RemoveServiceDTO request);
}

