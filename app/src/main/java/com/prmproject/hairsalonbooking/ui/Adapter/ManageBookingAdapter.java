package com.prmproject.hairsalonbooking.ui.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.Booking;
import com.prmproject.hairsalonbooking.data.model.type.BookingStatus;

import java.util.List;

public class ManageBookingAdapter extends RecyclerView.Adapter<ManageBookingAdapter.BookingViewHolder> {

    private final List<Booking> bookings;
    private final OnChangeStatusClickListener onChangeStatusClickListener;

    public ManageBookingAdapter(List<Booking> bookings, OnChangeStatusClickListener onChangeStatusClickListener) {
        this.bookings = bookings;
        this.onChangeStatusClickListener = onChangeStatusClickListener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.bind(booking);
        holder.spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos != booking.getStatusInt()) {
                    BookingStatus selectedStatus = BookingStatus.values()[pos];
                    booking.setStatus(selectedStatus.getValue());
                    onChangeStatusClickListener.onChangeStatusClick(booking);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public interface OnChangeStatusClickListener {
        void onChangeStatusClick(Booking booking);
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView textBookingId, textTotalPrice, textBookingDate, textCustomerName, textCustomerPhone;
        Spinner spinnerStatus;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            textBookingId = itemView.findViewById(R.id.textBookingId);
            textTotalPrice = itemView.findViewById(R.id.textTotalPrice);
            textBookingDate = itemView.findViewById(R.id.textBookingDate);
            textCustomerName = itemView.findViewById(R.id.textCustomerName);
            textCustomerPhone = itemView.findViewById(R.id.textCustomerPhone);
            spinnerStatus = itemView.findViewById(R.id.spinnerStatus);
        }

        public void bind(Booking booking) {
            Log.d("Status", booking.toString());
            textBookingId.setText("Booking ID: " + booking.getBookingId());
            textTotalPrice.setText("Total Price: $" + booking.getTotalPrice());
            textBookingDate.setText("Date: " + booking.getCreateDate());
            if (booking.getCustomer() != null) {
                textCustomerName.setText("Customer: " + booking.getCustomer().getUserName());
                textCustomerPhone.setText("Phone: " + booking.getCustomer().getPhone());
            }

            String[] statusArray = new String[BookingStatus.values().length];
            for (int i = 0; i < BookingStatus.values().length; i++) {
                statusArray[i] = BookingStatus.values()[i].name();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_item, statusArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStatus.setAdapter(adapter);

            int currentStatusIndex = booking.getStatusInt();
            spinnerStatus.setSelection(currentStatusIndex);
        }
    }
}
