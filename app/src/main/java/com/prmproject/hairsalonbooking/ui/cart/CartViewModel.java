package com.prmproject.hairsalonbooking.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.repository.BookingRepository;
import com.prmproject.hairsalonbooking.data.repository.UserProfileRepository;

public class CartViewModel  extends ViewModel {

    private BookingRepository bookingRepository;
    private UserProfileRepository userProfileRepository;

    public CartViewModel() {
        bookingRepository = new BookingRepository();
        userProfileRepository = new UserProfileRepository();
    }

    public LiveData<ObjectResponse> createBooking(RequestDTO.BookingRequestDTO model) {
        return bookingRepository.createBooking(model);
    }

    public LiveData<ObjectResponse> getCurrentUser() {
        return userProfileRepository.getCurrentUser();
    }
}