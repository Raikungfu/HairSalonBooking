package com.prmproject.hairsalonbooking.ui.Auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prmproject.hairsalonbooking.data.model.dataToReceive.MessageResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.TokenResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.UserLogin;
import com.prmproject.hairsalonbooking.data.model.dataToSend.UserRegister;
import com.prmproject.hairsalonbooking.data.repository.AuthRepository;

public class AuthViewModel extends ViewModel {
    private AuthRepository authRepository;

    public AuthViewModel() {
        authRepository = new AuthRepository();
    }

    public LiveData<TokenResponse> login(UserLogin user) {
        return authRepository.login(user);
    }

    public LiveData<MessageResponse<UserRegister>> register(UserRegister user) {
        return authRepository.register(user);
    }
}
