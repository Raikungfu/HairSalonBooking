package com.prmproject.hairsalonbooking.ui.Auth;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.prmproject.hairsalonbooking.MainActivity;
import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.dataToSend.UserRegister;
import com.prmproject.hairsalonbooking.ui.Auth.AuthViewModel;

public class RegisterFragment extends Fragment {
    private EditText usernameEditText, passwordEditText, emailEditText, phoneEditText;
    private AuthViewModel authViewModel;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        usernameEditText = view.findViewById(R.id.txt_username);
        passwordEditText = view.findViewById(R.id.txt_password);
        phoneEditText = view.findViewById(R.id.txt_phone);
        emailEditText = view.findViewById(R.id.txt_email);
        Button regButton = view.findViewById(R.id.container_button_primary_big_inactive);

        regButton.setOnClickListener(v -> {
            String Username = usernameEditText.getText().toString();
            String Password = passwordEditText.getText().toString();
            String Phone = phoneEditText.getText().toString();
            String Email = emailEditText.getText().toString();

            if (Username.isEmpty() || Password.isEmpty() || Phone.isEmpty() || Email.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            } else {
                UserRegister user = new UserRegister(Username, Password, Email, Phone);
                authViewModel.register(user).observe(getViewLifecycleOwner(), response -> {
                    if (response != null && response.getData() != null) {
                        Toast.makeText(getContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_navigation_register_to_navigation_login);
                    } else {
                        Toast.makeText(getContext(), "Registration failed! " + response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        TextView linkToLogin = view.findViewById(R.id.txt_login);
        linkToLogin.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_register_to_navigation_login);
        });


        ImageButton showPasswordButton = view.findViewById(R.id.button_show_password);

        showPasswordButton.setOnClickListener(v -> {
            if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                showPasswordButton.setImageResource(R.drawable.ic_visibility_off);
            } else {
                passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                showPasswordButton.setImageResource(R.drawable.ic_visibility);
            }
            passwordEditText.setSelection(passwordEditText.length());
        });


        return view;
    }
}
