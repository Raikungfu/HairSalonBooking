package com.prmproject.hairsalonbooking.data.repository.Generic;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ErrorResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;

import retrofit2.Response;

public class ResponseObjectData<T> {
    public ResponseObjectData() {
    }

    public MutableLiveData<ObjectResponse<T>> getObjectData(Response<ObjectResponse<T>> response) {
        MutableLiveData<ObjectResponse<T>> tokenLiveData = new MutableLiveData<>();
        if (response.isSuccessful() && response.body() != null) {
            tokenLiveData.setValue(new ObjectResponse<>(response.body().getData(), null, true));
        } else {
            String errorMessage = "Unknown error occurred.";
            ErrorResponse apiError = new ErrorResponse();
            if (response.errorBody() != null) {
                try {
                    String errorBodyString = response.errorBody().string();
                    apiError = new Gson().fromJson(errorBodyString, ErrorResponse.class);
                    errorMessage = apiError.convertErrorToString();
                } catch (Exception e) {
                    errorMessage = "Error parsing error response.";
                    e.printStackTrace();
                }
            }
            String finalErrorMessage = apiError.getMessage() != null ? apiError.getMessage() : errorMessage;

            tokenLiveData.setValue(new ObjectResponse<>(apiError, finalErrorMessage));
        }

        return tokenLiveData;
    }
}