package com.prmproject.hairsalonbooking.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.repository.BookingRepository;
import com.prmproject.hairsalonbooking.data.repository.UserRepository;

public class ManageUserViewModel extends ViewModel {
    private UserRepository userRepository;

    public ManageUserViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<ListResponse> getListUser() {
        return userRepository.getListUser();
    }

    public LiveData<ObjectResponse> createUser(RequestDTO.CreateAccountDTO model) {
        return userRepository.createUser(model);
    }

    public LiveData<ObjectResponse> updateUser(int id, RequestDTO.UpdateAccountDTO model) {
        return userRepository.updateUser(id, model);
    }

    public LiveData<ObjectResponse> changeUserStatus(int userId) {
        return userRepository.changeStatusAccount(userId);
    }
}