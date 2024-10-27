package com.prmproject.hairsalonbooking.data.remote;

import com.prmproject.hairsalonbooking.data.model.User;
import com.prmproject.hairsalonbooking.data.model.UserProfile;
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
import okhttp3.ResponseBody;

public interface UserApiService {

    @POST("api/v1/users/changeStatus/{id}")
    Call<ObjectResponse<String>> changeStatusAccount(@Path("id") int id);

    @GET("api/v1/users/usersList")
    Call<ListResponse<User>> getListUser();

    @GET("api/v1/users/searchAccountByName/{fullName}")
    Call<ObjectResponse<List<UserProfile>>> getUserByName(@Path("fullName") String fullName);

    @GET("api/v1/users/GetUserById/{userId}")
    Call<ObjectResponse<UserProfile>> getUserById(@Path("userId") int userId);

    @GET("api/v1/users/PagingUserList")
    Call<ObjectResponse<List<UserProfile>>> getUserPaging(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    @GET("api/v1/users/GetUserByUsernamePaging/{userName}")
    Call<ObjectResponse<List<UserProfile>>> getUserByUsernamePaging(
            @Path("userName") String userName,
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );

    @POST("api/v1/users/CreateAccount")
    Call<ObjectResponse<String>> createAccount(@Body RequestDTO.CreateAccountDTO request);

    @POST("api/v1/users/updateAccount/{id}")
    Call<ObjectResponse<String>> updateAccount(@Path("id") int id, @Body RequestDTO.UpdateAccountDTO request);

    @GET("api/v1/users/current")
    Call<ObjectResponse<User>> getCurrentUser();
}