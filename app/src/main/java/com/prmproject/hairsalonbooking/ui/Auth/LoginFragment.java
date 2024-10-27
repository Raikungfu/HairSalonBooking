package com.prmproject.hairsalonbooking.ui.Auth;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.dataToSend.UserLogin;
import com.prmproject.hairsalonbooking.network.RetrofitClient;
import com.prmproject.hairsalonbooking.ui.Auth.AuthViewModel;

public class LoginFragment extends Fragment {
    private EditText usernameEditText, passwordEditText;
    private AuthViewModel authViewModel;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        usernameEditText = view.findViewById(R.id.txt_username);
        passwordEditText = view.findViewById(R.id.txt_password);
        Button loginButton = view.findViewById(R.id.container_button_primary_big_inactive);

        loginButton.setOnClickListener(v -> {
            String Username = usernameEditText.getText().toString();
            String Password = passwordEditText.getText().toString();

            if (Username.isEmpty() || Password.isEmpty()) {
                Toast.makeText(requireActivity(), "Please enter complete information...", Toast.LENGTH_LONG).show();

            } else {
                UserLogin user = new UserLogin(Username, Password);
                authViewModel.login(user).observe(getViewLifecycleOwner(), response -> {
                    if (response != null && response.isStatus()) {
                        Toast.makeText(requireActivity(), "Login successful!"+ response.getMessage(), Toast.LENGTH_LONG).show();

                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        String token = response.getToken();
                        editor.putString("TOKEN_KEY", token);

                        RetrofitClient.updateToken(token);
                        editor.apply();
                        navController.navigate(R.id.navigation_home);
                    } else {
                        Toast.makeText(requireActivity(), "Login failed! " + response.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        TextView linkToSignUp = view.findViewById(R.id.txt_register);
        linkToSignUp.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_login_to_navigation_register);
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
