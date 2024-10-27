package com.prmproject.hairsalonbooking.ui.profile;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.User;
import com.prmproject.hairsalonbooking.data.model.type.UserRole;
import com.prmproject.hairsalonbooking.databinding.FragmentProfileBinding;
import com.prmproject.hairsalonbooking.network.RetrofitClient;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;
    private NavController navController;

    private CardView customerView;
    private CardView adminView;
    private CardView managerView;
    private CardView stylistView;
    private CardView staffView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        customerView = binding.customerView;
        adminView = binding.adminView;
        managerView = binding.managerView;
        stylistView = binding.stylistView;
        staffView = binding.staffView;

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        profileViewModel.getCurrentUser().observe(getViewLifecycleOwner(), userResponse -> {
            if (userResponse != null && userResponse.isSuccess()) {
                User user = (User) userResponse.getData();
                binding.tvUsername.setText(user.getUserName());
                binding.tvEmail.setText(user.getPhone());

                String roleText = UserRole.values()[user.getRoleInt()].toString();
                binding.tvUserRole.setText(roleText);

                displayContentBasedOnRole(roleText);

            } else if (userResponse.getError() != null && userResponse.getError().getStatus() == -5) {
                Toast.makeText(requireActivity(), "Session expired. Please log in again.", Toast.LENGTH_SHORT).show();
                logout();
            } else {
                Toast.makeText(requireActivity(), "Failed to load profile: " + userResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.logoutBtn.setOnClickListener(v -> logout());

        return binding.getRoot();
    }

    private void displayContentBasedOnRole(String role) {
        switch (role) {
            case "Customer":
                binding.tvUserRole.setBackgroundColor(getResources().getColor(R.color.colorCustomer));
                customerView.setVisibility(View.VISIBLE);
                binding.bookingHistoryBtn.setOnClickListener(v -> {
                    navController.navigate(R.id.navigation_booking_history);
                });
                break;

            case "Admin":
                binding.tvUserRole.setBackgroundColor(getResources().getColor(R.color.colorAdmin));
                binding.manageUserBtn.setOnClickListener(v -> {
                    navController.navigate(R.id.navigation_manage_user);
                });
                adminView.setVisibility(View.VISIBLE);
                break;

            case "Stylist":
                binding.tvUserRole.setBackgroundColor(getResources().getColor(R.color.colorStylist));
                stylistView.setVisibility(View.VISIBLE);
                binding.checkBookingBtn.setOnClickListener(v -> {
                    navController.navigate(R.id.manage_status_booking_Btn);
                });
                break;

            case "Manager":
                binding.tvUserRole.setBackgroundColor(getResources().getColor(R.color.colorManager));
                staffView.setVisibility(View.VISIBLE);
                binding.manageStatusBookingBtn.setOnClickListener(v -> {
                    navController.navigate(R.id.navigation_manage_booking);
                });
                managerView.setVisibility(View.VISIBLE);
                binding.manageServiceBtn.setOnClickListener(v -> {
                    navController.navigate(R.id.navigation_manage_hair_service);
                });
                break;
            case "Staff":
                binding.tvUserRole.setBackgroundColor(getResources().getColor(R.color.colorStaff));
                binding.manageStatusBookingBtn.setOnClickListener(v -> {
                    navController.navigate(R.id.navigation_manage_booking);
                });
                staffView.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void logout() {
        RetrofitClient.updateToken(null);
        deleteToken("BookingPrefs");
        deleteToken("UserSession");

        navController.navigate(R.id.navigation_login);
    }

    private void deleteToken(String s) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(s, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
