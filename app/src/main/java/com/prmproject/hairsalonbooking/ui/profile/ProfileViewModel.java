package com.prmproject.hairsalonbooking.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.repository.UserProfileRepository;

public class ProfileViewModel extends ViewModel {
    private UserProfileRepository userProfileRepository;

    public ProfileViewModel() {
        userProfileRepository = new UserProfileRepository();
    }

    public LiveData<ObjectResponse> getCurrentUser() {
        return userProfileRepository.getCurrentUser();
    }
}