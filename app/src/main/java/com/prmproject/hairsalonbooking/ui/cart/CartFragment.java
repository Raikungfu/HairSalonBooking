package com.prmproject.hairsalonbooking.ui.cart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.Booking;
import com.prmproject.hairsalonbooking.data.model.BookingInfo;
import com.prmproject.hairsalonbooking.data.model.UserProfile;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.model.type.VnPayMethod;
import com.prmproject.hairsalonbooking.databinding.FragmentCartBinding;
import com.prmproject.hairsalonbooking.network.RetrofitClient;
import com.prmproject.hairsalonbooking.service.PaymentService;
import com.prmproject.hairsalonbooking.ui.Adapter.BookingAdapter;
import com.prmproject.hairsalonbooking.ui.home.HomeViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class CartFragment extends Fragment {
    private FragmentCartBinding binding;
    private RecyclerView bookingRecyclerView;
    private BookingAdapter bookingAdapter;
    private List<BookingInfo> bookingList = new ArrayList<>();
    private Map<Integer, BookingInfo> bookingMap = new HashMap<>();
    private PaymentService paymentService;
    private CartViewModel cartViewModel;
    private Spinner paymentMethodSpinner;
    private NavController navController;
    private String username;
    private String phone;
    private String password;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        bookingRecyclerView = binding.bookingRecyclerView;
        paymentService = new PaymentService(requireContext());
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        paymentMethodSpinner = binding.paymentMethodSpinner;

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        loadBookingData();

        bookingRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        bookingAdapter = new BookingAdapter(bookingList, this::onHairServiceSelected);
        bookingRecyclerView.setAdapter(bookingAdapter);

        checkLoginStatus();

        binding.cancelButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        binding.bookingButton.setOnClickListener(v -> {
            initiatePaymentForSelectedBooking();
        });

        return binding.getRoot();
    }


    private void checkLoginStatus() {
        cartViewModel.getCurrentUser().observe(getViewLifecycleOwner(), userResponse -> {
            if (userResponse != null && userResponse.isSuccess()) {
                binding.bookingButton.setVisibility(View.VISIBLE);
            } else {
                showLoginPrompt();
            }
        });
    }

    private void showLoginPrompt() {
        deleteToken();
        new AlertDialog.Builder(requireContext())
                .setTitle("Chưa đăng nhập")
                .setMessage("Bạn có muốn đăng nhập không?")
                .setPositiveButton("Đăng nhập", (dialog, which) -> {
                    navController.navigate(R.id.navigation_login);
                })
                .setNegativeButton("Đăng ký", (dialog, which) -> {
                    showRegistrationForm();
                })
                .show();
    }
    private void showRegistrationForm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Đăng ký");

        View registrationView = getLayoutInflater().inflate(R.layout.dialog_registration_form, null);
        builder.setView(registrationView);

        EditText nameEditText = registrationView.findViewById(R.id.edit_text_name);
        EditText phoneEditText = registrationView.findViewById(R.id.edit_text_phone);
        EditText passwordEditText = registrationView.findViewById(R.id.edit_text_password);
        Button registerButton = registrationView.findViewById(R.id.button_register);

        ImageButton showPasswordButton = registrationView.findViewById(R.id.button_show_password);

        showPasswordButton.setOnClickListener(view -> {
            if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                showPasswordButton.setImageResource(R.drawable.ic_visibility_off);
            } else {
                passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                showPasswordButton.setImageResource(R.drawable.ic_visibility);
            }
            passwordEditText.setSelection(passwordEditText.length());
        });


        AlertDialog dialog = builder.create();

        registerButton.setOnClickListener(view -> {
            username = nameEditText.getText().toString();
            phone = phoneEditText.getText().toString();
            password = passwordEditText.getText().toString();

            if (username.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                dialog.dismiss();
                binding.bookingButton.setVisibility(View.VISIBLE);
                Toast.makeText(requireContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }


    private void initiatePaymentForSelectedBooking() {
        String selectedPaymentMethod = paymentMethodSpinner.getSelectedItem().toString();

        if (bookingMap.size() > 0) {
            String voucherCodeText = binding.voucherInput.getText().toString().trim();
            Integer voucherCode = null;
            if (!voucherCodeText.isEmpty()) {
                voucherCode = Integer.parseUnsignedInt(voucherCodeText);
            }

            cartViewModel.createBooking(new RequestDTO.BookingRequestDTO(username, phone, voucherCode, password, 1,
                            new ArrayList<>(bookingMap.keySet()), new ArrayList<>(bookingMap.values().stream().map(BookingInfo::getStylistId)
                            .collect(Collectors.toList())))).observe(getViewLifecycleOwner(), objectResponse -> {
                if (objectResponse != null) {
                    if (objectResponse.isSuccess()) {
                        bookingMap.clear();
                        bookingList.clear();
                        bookingAdapter.notifyDataSetChanged();
                        saveBookingData();
                        Booking booking = (Booking) objectResponse.getData();

                        switch (selectedPaymentMethod) {
                            case "Thanh toán khi nhận hàng":
                                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                                navController.navigate(R.id.navigation_home);
                                break;
                            case "Thẻ ATM nội địa":
                                paymentService.createPayment(new RequestDTO.VnPaymentRequestModel(booking.getBookingId(), booking.getTotalPrice(), VnPayMethod.ATM));
                                break;
                            case "Thẻ quốc tế":
                                paymentService.createPayment(new RequestDTO.VnPaymentRequestModel(booking.getBookingId(), booking.getTotalPrice(), VnPayMethod.CreditCard));
                                break;
                            default:
                                break;
                        }
                        Toast.makeText(requireContext(), "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Đặt lịch thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(requireContext(), "Vui lòng chọn dịch vụ để thanh toán", Toast.LENGTH_SHORT).show();
        }
    }

    private void onHairServiceSelected(BookingInfo bookingInfo) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Hủy chọn dịch vụ")
                .setMessage("Bạn có chắc chắn muốn hủy chọn dịch vụ \"" + bookingInfo.getServiceName() + "\" không?")
                .setPositiveButton("Có", (dialog, which) -> {
                    int serviceId = bookingInfo.getGetServiceId();
                    bookingMap.remove(serviceId);

                    bookingList.remove(bookingInfo);
                    bookingAdapter.notifyDataSetChanged();

                    saveBookingData();
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private void saveBookingData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(bookingMap);

        editor.putString("booking_map", json);
        editor.apply();
    }

    private void loadBookingData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("booking_map", null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<Integer, BookingInfo>>() {}.getType();
            bookingMap = gson.fromJson(json, type);

            if (bookingMap != null) {
                bookingList.addAll(bookingMap.values());
            }
        }
    }

    private void deleteToken() {
        RetrofitClient.updateToken(null);
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

