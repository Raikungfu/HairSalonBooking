package com.prmproject.hairsalonbooking.ui.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.databinding.FragmentBookingHistoryBinding;
import com.prmproject.hairsalonbooking.ui.Adapter.BookingHistoryAdapter;

import java.util.List;

public class BookingHistoryFragment extends Fragment {
    private FragmentBookingHistoryBinding binding;
    private BookingViewModel bookingViewModel;
    private BookingHistoryAdapter bookingAdapter;
    private RecyclerView bookingRecyclerView;
    private List<RequestDTO.BookingHistoryDTO> bookingList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookingHistoryBinding.inflate(inflater, container, false);
        bookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);

        bookingRecyclerView = binding.recyclerView;

        bookingViewModel.getBookingHistory().observe(getViewLifecycleOwner(), bookingListResponse -> {
            if (bookingListResponse != null && bookingListResponse.isSuccess()) {
                bookingList = bookingListResponse.getData();
                bookingAdapter = new BookingHistoryAdapter(bookingList);
                bookingRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
                bookingRecyclerView.setAdapter(bookingAdapter);
            }
        });

        return binding.getRoot();
    }

    private void onBookingSelected(RequestDTO.BookingHistoryDTO booking) {

    }
}
