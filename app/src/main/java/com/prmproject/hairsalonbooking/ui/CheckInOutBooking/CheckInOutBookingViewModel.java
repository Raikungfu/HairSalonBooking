package com.prmproject.hairsalonbooking.ui.CheckInOutBooking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.repository.BookingRepository;

public class CheckInOutBookingViewModel extends ViewModel {
    private BookingRepository bookingRepository;

    public CheckInOutBookingViewModel() {
        bookingRepository = new BookingRepository();
    }

    public LiveData<ListResponse> getBookingsForStylist() {
        return bookingRepository.getBookingsForStylist();
    }

    public LiveData<ObjectResponse> changeBookingStatus(int id, RequestDTO.ChangeBookingStatusDTO model) {
        return bookingRepository.changeBookingStatus(id, model);
    }
}
