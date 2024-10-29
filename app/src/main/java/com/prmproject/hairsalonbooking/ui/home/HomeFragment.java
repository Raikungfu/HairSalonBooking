package com.prmproject.hairsalonbooking.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.BookingInfo;
import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.type.Stylist;
import com.prmproject.hairsalonbooking.databinding.FragmentHomeBinding;
import com.prmproject.hairsalonbooking.ui.Adapter.HairServiceAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private HairServiceAdapter hairServiceAdapter;
    private RecyclerView hairServiceRecyclerView;
    private List<HairService> hairServiceList;
    private TextView bookingButton;
    private Map<Integer, BookingInfo> bookingMap = new HashMap<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        hairServiceRecyclerView = binding.hairServiceRecyclerView;
        bookingButton = binding.bookingButton;

        homeViewModel.getListHairService().observe(getViewLifecycleOwner(), hairServiceListResponse -> {
            if (hairServiceListResponse != null && hairServiceListResponse.isSuccess()) {
                hairServiceList = hairServiceListResponse.getData();
                List<HairService> filteredHairServiceList = new ArrayList<>();
                for (HairService hair: hairServiceList) {
                    if (hair.getStatus() == 1) {
                        if (bookingMap.containsKey(hair.getServiceId())) {
                            hair.setSelected(true);
                        }
                        filteredHairServiceList.add(hair);
                    }
                }
                hairServiceList = filteredHairServiceList;
                hairServiceAdapter = new HairServiceAdapter(hairServiceList, this::onHairServiceSelected);
                hairServiceRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
                hairServiceRecyclerView.setAdapter(hairServiceAdapter);
            }
        });

        loadBookingData();

        bookingButton.setOnClickListener(v -> {
            handleBooking();
        });

        return binding.getRoot();
    }

    private void onHairServiceSelected(HairService selectedService) {
        if (selectedService.isSelected()) {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Hủy chọn dịch vụ")
                    .setMessage("Bạn có chắc chắn muốn hủy chọn dịch vụ \"" + selectedService.getServiceName() + "\" không?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        selectedService.setSelected(false);
                        bookingMap.remove(selectedService.getServiceId());
                        hairServiceAdapter.notifyDataSetChanged();
                        saveBooking();
                    })
                    .setNegativeButton("Không", null)
                    .show();
        } else {
            fetchStylistsForService(selectedService, selectedService.getServiceId());
        }
    }

    private void fetchStylistsForService(HairService selectedService, int serviceId) {
        homeViewModel.getListStylistService(serviceId).observe(getViewLifecycleOwner(), stylistListResponse -> {
            if (stylistListResponse != null && stylistListResponse.isSuccess()) {
                List<Stylist> stylistList = stylistListResponse.getData();

                if (stylistList != null && !stylistList.isEmpty()) {
                    showStylistSelectionDialog(selectedService, stylistList, serviceId);
                } else {
                    Toast.makeText(getContext(), stylistListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else if(stylistListResponse != null && stylistListResponse.getStatus() == 401){
                Toast.makeText(getContext(), "Bạn cần đăng nhập để đặt dịch vụ.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "Không thể lấy danh sách stylist." + stylistListResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showStylistSelectionDialog(HairService selectedService, List<Stylist> stylistList, int serviceId) {
        String[] stylistNames = new String[stylistList.size()];
        for (int i = 0; i < stylistList.size(); i++) {
            stylistNames[i] = stylistList.get(i).getStylistName();
        }

        new AlertDialog.Builder(requireContext())
                .setTitle("Chọn Stylist")
                .setItems(stylistNames, (dialog, which) -> {
                    selectedService.setSelected(true);
                    Stylist selectedStylist = stylistList.get(which);

                    String serviceName = selectedService.getServiceName();
                    double price = selectedService.getPrice();
                    String estimatedTime = selectedService.getEstimateTime();

                    BookingInfo bookingInfo = new BookingInfo(selectedStylist.getStylistId(), serviceName, selectedStylist.getStylistName(), price, estimatedTime, serviceId);
                    bookingMap.put(serviceId, bookingInfo);

                    hairServiceAdapter.notifyDataSetChanged();
                    saveBooking();
                    Toast.makeText(getContext(), "Bạn đã chọn stylist " + selectedStylist.getStylistName() + " cho service " + selectedService.getServiceName(), Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void handleBooking() {
        if (bookingMap.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng chọn ít nhất một dịch vụ và stylist để đặt lịch!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_cart);
        }
    }

    public void saveBooking(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(bookingMap);
        editor.putString("booking_map", json);

        editor.apply();

        Log.d("BookingMap", json);
    }

    private void loadBookingData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("booking_map", null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<Integer, BookingInfo>>() {}.getType();

            bookingMap = gson.fromJson(json, type);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

