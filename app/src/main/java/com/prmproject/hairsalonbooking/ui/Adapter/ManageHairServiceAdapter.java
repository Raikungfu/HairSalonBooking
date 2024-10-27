package com.prmproject.hairsalonbooking.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.HairService;

import java.util.List;

public class ManageHairServiceAdapter extends RecyclerView.Adapter<ManageHairServiceAdapter.ServiceViewHolder> {

    private List<HairService> services;
    private OnEditClickListener onEditClick;
    private OnDeleteClickListener onDeleteClick;

    public interface OnEditClickListener {
        void onEditClick(HairService service);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int serviceId);
    }

    public ManageHairServiceAdapter(List<HairService> services, OnEditClickListener onEditClick, OnDeleteClickListener onDeleteClick) {
        this.services = services;
        this.onEditClick = onEditClick;
        this.onDeleteClick = onDeleteClick;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        HairService service = services.get(position);
        holder.bind(service);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        ImageButton editButton;
        ImageButton deleteButton;

        ServiceViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.textViewServiceName);
            priceTextView = view.findViewById(R.id.textViewServicePrice);
            editButton = view.findViewById(R.id.buttonEditService);
            deleteButton = view.findViewById(R.id.buttonDeleteService);
        }

        void bind(HairService service) {
            nameTextView.setText(service.getServiceName());
            priceTextView.setText(String.valueOf(service.getPrice()));

            editButton.setOnClickListener(v -> onEditClick.onEditClick(service));
            deleteButton.setOnClickListener(v -> onDeleteClick.onDeleteClick(service.getServiceId()));
        }
    }
}
