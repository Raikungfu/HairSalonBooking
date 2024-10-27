package com.prmproject.hairsalonbooking.ui.booking;

import android.os.Bundle;
import android.util.Log;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.Booking;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.model.type.BookingStatus;
import com.prmproject.hairsalonbooking.ui.Adapter.BookingAdapter;
import com.prmproject.hairsalonbooking.ui.Adapter.BookingHistoryAdapter;
import com.prmproject.hairsalonbooking.ui.Adapter.ManageBookingAdapter;

import java.util.ArrayList;
import java.util.List;
public class ManageBookingFragment extends Fragment {

    private RecyclerView recyclerViewBookings;
    private ManageBookingAdapter bookingAdapter;
    private List<Booking> bookings = new ArrayList<>();
    private BookingViewModel bookingViewModel;
    private int currentPage = 1;
    private boolean isLoading = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_booking, container, false);
        bookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);

        recyclerViewBookings = view.findViewById(R.id.recyclerViewBookings);

        bookingAdapter = new ManageBookingAdapter(bookings, this::changeBookingStatus);
        recyclerViewBookings.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewBookings.setAdapter(bookingAdapter);

        setupObservers();
        setupScrollListener();

        return view;
    }

    private void setupObservers() {
        bookingViewModel.getBookings(currentPage, 10).observe(getViewLifecycleOwner(), bookingListResponse -> {
            if (bookingListResponse != null && bookingListResponse.isSuccess()) {
                if (bookingListResponse.getData() != null) {
                    bookings.addAll(bookingListResponse.getData());
                    bookingAdapter.notifyDataSetChanged();
                }
                isLoading = false;
            }
        });
    }

    private void setupScrollListener() {
        recyclerViewBookings.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null && !isLoading && layoutManager.findLastCompletelyVisibleItemPosition() == bookings.size() - 1) {
                    currentPage++;
                    isLoading = true;
                    loadMoreBookings();
                }
            }
        });
    }

    private void loadMoreBookings() {
        bookingViewModel.getBookings(currentPage, 10).observe(getViewLifecycleOwner(), bookingListResponse -> {
            if (bookingListResponse != null && bookingListResponse.isSuccess()) {
                if (bookingListResponse.getData() != null) {
                    bookings.addAll(bookingListResponse.getData());
                    bookingAdapter.notifyDataSetChanged();
                }
                isLoading = false;
            }
        });
    }

    private void changeBookingStatus(Booking booking) {
        int index = bookings.indexOf(booking);
        if (index != -1) {
            bookingViewModel.changeBookingStatus(booking.getBookingId(), new RequestDTO.ChangeBookingStatusDTO(booking.getStatus())).observe(getViewLifecycleOwner(), objectResponse -> {
                if(objectResponse.getData() != null) {
                    Toast.makeText(getContext(), objectResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
                else if (objectResponse.getError() != null) {
                    Toast.makeText(getContext(), objectResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
