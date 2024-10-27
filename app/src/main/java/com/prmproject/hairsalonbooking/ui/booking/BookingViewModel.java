package com.prmproject.hairsalonbooking.ui.booking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prmproject.hairsalonbooking.data.model.dataToReceive.ListResponse;
import com.prmproject.hairsalonbooking.data.model.dataToReceive.ObjectResponse;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.repository.BookingRepository;

public class BookingViewModel extends ViewModel {
    private BookingRepository bookingRepository;

    public BookingViewModel() {
        bookingRepository = new BookingRepository();
    }

    public LiveData<ListResponse> getBookingHistory() {
        return bookingRepository.getBookingHistory();
    }

    public LiveData<ListResponse> getBookings(int page, int pageSize) {
        return bookingRepository.getListBooking(page, pageSize);
    }

    public LiveData<ObjectResponse> changeBookingStatus(int id, RequestDTO.ChangeBookingStatusDTO model) {
        return bookingRepository.changeBookingStatus(id, model);
    }
}
