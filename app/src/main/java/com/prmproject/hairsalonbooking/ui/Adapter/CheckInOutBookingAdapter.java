package com.prmproject.hairsalonbooking.ui.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.Booking;
import com.prmproject.hairsalonbooking.data.model.BookingDetail;
import com.prmproject.hairsalonbooking.data.model.type.BookingStatus;

import java.util.List;

public class CheckInOutBookingAdapter extends RecyclerView.Adapter<CheckInOutBookingAdapter.BookingViewHolder> {
    private List<Booking> bookings;
    private Context context;
    private BookingStatusListener listener;

    public CheckInOutBookingAdapter(List<Booking> bookings, BookingStatusListener listener, Context context) {
        this.bookings = bookings;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_check_in_out_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookings.get(position);

        holder.textBookingId.setText("Booking ID: " + booking.getBookingId());
        holder.textTotalPrice.setText("Total price: " + booking.getTotalPrice());
        holder.textCustomerName.setText("Customer: " + booking.getCustomer().getUserName());

        holder.textBookingStatus.setText("Status: " + booking.getStatus().name());

        holder.buttonToggleDetails.setOnClickListener(v -> {
            if (holder.layoutBookingDetails.getVisibility() == View.GONE) {
                holder.layoutBookingDetails.setVisibility(View.VISIBLE);
                populateBookingDetails(holder.layoutBookingDetails, booking.getBookingDetails());
                holder.buttonToggleDetails.setImageResource(android.R.drawable.arrow_down_float);
            } else {
                holder.layoutBookingDetails.setVisibility(View.GONE);
                holder.buttonToggleDetails.setImageResource(android.R.drawable.arrow_up_float);
            }
        });

        holder.buttonCheckIn.setOnClickListener(v -> listener.onCheckIn(booking));
        holder.buttonCheckOut.setOnClickListener(v -> listener.onCheckOut(booking));
    }

    private void populateBookingDetails(LinearLayout layout, List<BookingDetail> bookingDetails) {
        layout.removeAllViews();

        TextView labelTextView = new TextView(context);
        labelTextView.setText("Booking Details");
        labelTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        labelTextView.setTextColor(ContextCompat.getColor(context, R.color.textPrimary));
        labelTextView.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        labelParams.setMargins(0, 16, 0, 8);

        labelTextView.setLayoutParams(labelParams);
        layout.addView(labelTextView);

        for (BookingDetail detail : bookingDetails) {
            TextView textView = new TextView(context);
            textView.setText("Service: " + detail.getServiceName() + ", Stylist: " + detail.getStylistName());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setTextColor(ContextCompat.getColor(context, R.color.textSecondary));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 8, 0, 8);

            textView.setLayoutParams(params);
            layout.addView(textView);
        }
    }


    @Override
    public int getItemCount() {
        return bookings.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView textBookingId, textTotalPrice, textCustomerName, textBookingStatus;
        ImageButton buttonToggleDetails;
        LinearLayout layoutBookingDetails;
        Button buttonCheckIn, buttonCheckOut;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            textBookingId = itemView.findViewById(R.id.textBookingId);
            textTotalPrice = itemView.findViewById(R.id.textTotalPrice);
            textCustomerName = itemView.findViewById(R.id.textCustomerName);
            buttonToggleDetails = itemView.findViewById(R.id.buttonToggleDetails);
            layoutBookingDetails = itemView.findViewById(R.id.layoutBookingDetails);
            buttonCheckIn = itemView.findViewById(R.id.buttonCheckIn);
            buttonCheckOut = itemView.findViewById(R.id.buttonCheckOut);
            textBookingStatus = itemView.findViewById(R.id.textBookingStatus);
        }
    }

    public void updateBookingStatus(Booking booking, BookingStatus status) {
        int position = bookings.indexOf(booking);
        if (position != -1) {
            bookings.get(position).setStatus(status.ordinal());
            notifyItemChanged(position);
        }
    }


    public interface BookingStatusListener {
        void onCheckIn(Booking booking);
        void onCheckOut(Booking booking);
    }
}
