package com.prmproject.hairsalonbooking.ui.CheckInOutBooking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.Booking;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.model.type.BookingStatus;
import com.prmproject.hairsalonbooking.ui.Adapter.CheckInOutBookingAdapter;
import com.prmproject.hairsalonbooking.ui.booking.BookingViewModel;

import java.util.ArrayList;
import java.util.List;

public class CheckInOutBookingFragment extends Fragment implements CheckInOutBookingAdapter.BookingStatusListener {

    private RecyclerView recyclerViewBookings;
    private CheckInOutBookingAdapter bookingAdapter;
    private List<Booking> bookings = new ArrayList<>();
    private CheckInOutBookingViewModel checkInOutBookingViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_in_out_booking, container, false);
        checkInOutBookingViewModel = new ViewModelProvider(this).get(CheckInOutBookingViewModel.class);

        recyclerViewBookings = view.findViewById(R.id.recyclerViewCheckInOutBookings);
        bookingAdapter = new CheckInOutBookingAdapter(bookings, this, requireContext());
        recyclerViewBookings.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerViewBookings.setAdapter(bookingAdapter);

        setupObservers();

        return view;
    }

    private void setupObservers() {
        checkInOutBookingViewModel.getBookingsForStylist().observe(getViewLifecycleOwner(), bookingListResponse -> {
            if (bookingListResponse != null && bookingListResponse.isSuccess()) {
                if (bookingListResponse.getData() != null) {
                    bookings.addAll(bookingListResponse.getData());
                    bookingAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void changeBookingStatus(Booking booking, String action) {
        BookingStatus status = action.equals("checkIn") ? BookingStatus.Accepted : BookingStatus.Complete;
        checkInOutBookingViewModel.changeBookingStatus(booking.getBookingId(), new RequestDTO.ChangeBookingStatusDTO(status)).observe(getViewLifecycleOwner(), objectResponse -> {
            if (objectResponse.getData() != null) {
                Toast.makeText(getContext(), objectResponse.getMessage(), Toast.LENGTH_LONG).show();
                bookingAdapter.updateBookingStatus(booking, status);
            } else if (objectResponse.getError() != null) {
                Toast.makeText(getContext(), objectResponse.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCheckIn(Booking booking) {
        changeBookingStatus(booking, "checkIn");
    }

    @Override
    public void onCheckOut(Booking booking) {
        changeBookingStatus(booking, "checkOut");
    }
}
