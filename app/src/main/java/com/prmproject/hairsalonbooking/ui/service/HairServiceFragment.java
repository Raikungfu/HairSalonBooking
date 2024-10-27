package com.prmproject.hairsalonbooking.ui.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.ui.Adapter.HairServiceAdapter;
import com.prmproject.hairsalonbooking.ui.Adapter.ManageHairServiceAdapter;
import com.prmproject.hairsalonbooking.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HairServiceFragment extends Fragment {

    private RecyclerView recyclerView;
    private ManageHairServiceAdapter adapter;
    private FloatingActionButton buttonAddService;

    private List<HairService> serviceList;

    private HairServiceViewModel hairServiceViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hair_service, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewHairServices);
        buttonAddService = view.findViewById(R.id.buttonAddService);

        buttonAddService.setOnClickListener(v -> showServiceFormDialog(null));
        hairServiceViewModel = new ViewModelProvider(this).get(HairServiceViewModel.class);

        loadServices();
        return view;
    }

    private void loadServices() {
        hairServiceViewModel.getListHairService().observe(getViewLifecycleOwner(), hairServiceListResponse -> {
            if (hairServiceListResponse != null && hairServiceListResponse.isSuccess()) {
                serviceList = hairServiceListResponse.getData();
                adapter = new ManageHairServiceAdapter(serviceList, this::onEditService, this::onDeleteService);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void onEditService(HairService service) {
        showServiceFormDialog(service);
    }

    private void onDeleteService(int serviceId) {
        hairServiceViewModel.deleteHairService(serviceId, new RequestDTO.RemoveServiceDTO(2)).observe(getViewLifecycleOwner(), hairServiceResponse -> {
            if (hairServiceResponse != null && hairServiceResponse.isSuccess()) {
                Toast.makeText(requireContext(), "Service deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Failed to delete service", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(requireContext(), "Service deleted: " + serviceId, Toast.LENGTH_SHORT).show();
        loadServices();
    }

    private void showServiceFormDialog(HairService service) {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_service_form, null);

        EditText imageLinkEditText = dialogView.findViewById(R.id.editTextImageLink);
        EditText serviceNameEditText = dialogView.findViewById(R.id.editTextServiceName);
        EditText descriptionEditText = dialogView.findViewById(R.id.editTextDescription);
        EditText priceEditText = dialogView.findViewById(R.id.editTextPrice);
        EditText estimateTimeEditText = dialogView.findViewById(R.id.editTextEstimateTime);

        if (service != null) {
            imageLinkEditText.setText(service.getImageLink());
            serviceNameEditText.setText(service.getServiceName());
            descriptionEditText.setText(service.getDescription());
            priceEditText.setText(String.valueOf(service.getPrice()));
            estimateTimeEditText.setText(service.getEstimateTime());
        }

        new AlertDialog.Builder(requireContext())
                .setTitle(service == null ? "Add Service" : "Edit Service")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    try {
                        String imageLink = imageLinkEditText.getText().toString();
                        String serviceName = serviceNameEditText.getText().toString();
                        String description = descriptionEditText.getText().toString();
                        Double price = Double.parseDouble(priceEditText.getText().toString());
                        String estimateTime = estimateTimeEditText.getText().toString();

                        HairService newService = new HairService(service == null ? 0 : service.getServiceId(), imageLink, serviceName, description, price, estimateTime);

                        if (service == null) {
                            hairServiceViewModel.createHairService(new RequestDTO.CreateServiceDTO(imageLink, serviceName, description, price.intValue(), estimateTime)).observe(getViewLifecycleOwner(), hairServiceResponse -> {
                                if (hairServiceResponse != null && hairServiceResponse.getData() != null) {
                                    Toast.makeText(requireContext(), "Service added", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(requireContext(), "Failed to add service", Toast.LENGTH_SHORT).show();
                                }
                            });
                            serviceList.add(newService);
                        } else {
                            hairServiceViewModel.updateHairService(service.getServiceId(), new RequestDTO.UpdateServiceDTO(imageLink, serviceName, description, price.intValue(), estimateTime)).observe(getViewLifecycleOwner(), hairServiceResponse -> {
                                if (hairServiceResponse != null && hairServiceResponse.getData() != null) {
                                    Toast.makeText(requireContext(), "Service updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(requireContext(), "Failed to update service", Toast.LENGTH_SHORT).show();
                                }
                            });
                            int index = serviceList.indexOf(service);
                            serviceList.set(index, newService);
                        }
                        adapter.notifyDataSetChanged();
                    }catch (Exception e){
                        Toast.makeText(requireContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
