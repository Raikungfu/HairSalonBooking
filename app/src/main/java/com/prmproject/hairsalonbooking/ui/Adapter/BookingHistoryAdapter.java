package com.prmproject.hairsalonbooking.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;

import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.BookingViewHolder> {
    private List<RequestDTO.BookingHistoryDTO> bookingServices;

    public BookingHistoryAdapter(List<RequestDTO.BookingHistoryDTO> bookingServices) {
        this.bookingServices = bookingServices;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_service, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        RequestDTO.BookingHistoryDTO bookingService = bookingServices.get(position);
        holder.serviceName.setText(bookingService.getServiceName());
        holder.username.setText(bookingService.getUsername());
        holder.startDate.setText(bookingService.getStartDate());
        holder.totalPrice.setText(String.valueOf(bookingService.getTotalPrice()));
    }

    @Override
    public int getItemCount() {
        return bookingServices.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, username, startDate, totalPrice;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.serviceName);
            username = itemView.findViewById(R.id.username);
            startDate = itemView.findViewById(R.id.startDate);
            totalPrice = itemView.findViewById(R.id.totalPrice);
        }
    }
}
