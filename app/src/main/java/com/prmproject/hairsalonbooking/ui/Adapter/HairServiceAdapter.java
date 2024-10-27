package com.prmproject.hairsalonbooking.ui.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.HairService;

import java.util.List;

public class HairServiceAdapter extends RecyclerView.Adapter<HairServiceAdapter.HairServiceViewHolder> {
    private List<HairService> hairServiceList;
    private OnServiceClickListener serviceClickListener;

    public HairServiceAdapter(List<HairService> hairServiceList, OnServiceClickListener listener) {
        this.hairServiceList = hairServiceList;
        serviceClickListener = listener;
    }

    public interface OnServiceClickListener {
        void onServiceClick(HairService service);
    }

    @NonNull
    @Override
    public HairServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hair_service_item, parent, false);
        return new HairServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HairServiceViewHolder holder, int position) {
        HairService service = hairServiceList.get(position);
        if (service != null) {
            holder.serviceName.setText(service.getServiceName());
            holder.servicePrice.setText("₫" + service.getPrice());
            holder.serviceEstimateTime.setText(service.getEstimateTime());
            holder.serviceDescription.setText(service.getDescription());

            Glide.with(holder.itemView.getContext())
                    .load(service.getImageLink())
                    .placeholder(R.drawable.logor)
                    .error(R.drawable.logor)
                    .into(holder.serviceImage);

            if (service.isSelected()) {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorSecondaryVariant));
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }

            holder.itemView.setOnClickListener(v -> {
                if (serviceClickListener != null) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        serviceClickListener.onServiceClick(service);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return hairServiceList.size();
    }

    public static class HairServiceViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, servicePrice, serviceEstimateTime, serviceDescription;
        ImageView serviceImage;

        public HairServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.serviceName);
            servicePrice = itemView.findViewById(R.id.servicePrice);
            serviceEstimateTime = itemView.findViewById(R.id.serviceEstimateTime);
            serviceImage = itemView.findViewById(R.id.serviceImage);
            serviceDescription = itemView.findViewById(R.id.serviceDescription);
        }
    }
}
