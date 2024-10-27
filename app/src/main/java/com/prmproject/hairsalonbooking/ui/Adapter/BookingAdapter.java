package com.prmproject.hairsalonbooking.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.BookingInfo;
import com.prmproject.hairsalonbooking.data.model.HairService;

import java.util.List;

public class  BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<BookingInfo> bookingList;
    private OnServiceClickListener serviceClickListener;

    public BookingAdapter(List<BookingInfo> bookingList, OnServiceClickListener listener) {
        this.bookingList = bookingList;
        serviceClickListener = listener;
    }

    public interface OnServiceClickListener {
        void onServiceClick(BookingInfo service);
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingInfo bookingInfo = bookingList.get(position);
        holder.serviceName.setText(bookingInfo.getServiceName());
        holder.stylistName.setText("Stylist: " + bookingInfo.getStylistName());
        holder.price.setText("Price: " + bookingInfo.getPrice());
        holder.estimatedTime.setText("Estimated Time: " + bookingInfo.getEstimatedTime());

        holder.itemView.setOnClickListener(v -> {
            if (serviceClickListener != null) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    serviceClickListener.onServiceClick(bookingInfo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, stylistName, price, estimatedTime;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.service_name);
            stylistName = itemView.findViewById(R.id.stylist_name);
            price = itemView.findViewById(R.id.price);
            estimatedTime = itemView.findViewById(R.id.estimated_time);

        }
    }
}
