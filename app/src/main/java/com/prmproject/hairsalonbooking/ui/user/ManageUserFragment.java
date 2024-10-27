package com.prmproject.hairsalonbooking.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.prmproject.hairsalonbooking.data.model.User;
import com.prmproject.hairsalonbooking.data.model.dataToSend.RequestDTO;
import com.prmproject.hairsalonbooking.data.model.type.BookingStatus;
import com.prmproject.hairsalonbooking.data.model.type.UserRole;
import com.prmproject.hairsalonbooking.data.model.type.UserStatus;
import com.prmproject.hairsalonbooking.ui.Adapter.UserAdapter;

import java.util.List;

public class ManageUserFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> users;
    private ManageUserViewModel manageUserViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_user, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_users);
        FloatingActionButton fabAddUser = view.findViewById(R.id.fab_add_user);

        manageUserViewModel = new ViewModelProvider(this).get(ManageUserViewModel.class);

        manageUserViewModel.getListUser().observe(getViewLifecycleOwner(), listResponse -> {
            if (listResponse != null && listResponse.isSuccess()) {
                users = listResponse.getData();
                userAdapter = new UserAdapter(users, new UserAdapter.OnUserClickListener() {
                    @Override
                    public void onEditUser(User user) {
                        showUserDialog(user);
                    }
                }, user -> {
                    manageUserViewModel.changeUserStatus(user.getUserId()).observe(getViewLifecycleOwner(), objectResponse -> {
                        if (objectResponse != null && objectResponse.isSuccess()) {
                            user.setStatus(user.getStatusInt());
                            userAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Change user status successful!", Toast.LENGTH_LONG).show();
                        } else if (objectResponse.getError() != null) {
                            Toast.makeText(getContext(), objectResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                });

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(userAdapter);
            }
        });

        fabAddUser.setOnClickListener(v -> showUserDialog(null));
        return view;
    }

    private void showUserDialog(@Nullable User user) {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_user_form, null);
        AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();
        try{
            EditText editTextUserName = dialogView.findViewById(R.id.editText_userName);
            EditText editTextPassword = dialogView.findViewById(R.id.editText_password);
            EditText editTextPhone = dialogView.findViewById(R.id.editText_phone);
            Spinner spinnerRoleId = dialogView.findViewById(R.id.spinner_roleId);
            Button buttonSave = dialogView.findViewById(R.id.button_save);

            if (user != null) {
                editTextUserName.setText(user.getUserName());
                editTextPassword.setText(user.getPassword());
                editTextPhone.setText(user.getPhone());

                String[] statusArray = new String[UserRole.values().length];
                for (int i = 0; i < UserRole.values().length; i++) {
                    statusArray[i] = UserRole.values()[i].name();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, statusArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRoleId.setAdapter(adapter);

                int currentRoleIndex = (user.getRoleInt() != null) ? user.getRoleInt(): 0;
                spinnerRoleId.setSelection(currentRoleIndex);
            }

            buttonSave.setOnClickListener(v -> {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String phone = editTextPhone.getText().toString();
                int roleId = spinnerRoleId.getSelectedItemPosition();

                if (user == null) {
                    manageUserViewModel.createUser(new RequestDTO.CreateAccountDTO(userName, password, phone, UserRole.values()[roleId])).observe(getViewLifecycleOwner(), objectResponse -> {
                        if (objectResponse != null && objectResponse.isSuccess()) {
                            users.add(new User(users.size() + 1, userName, password, phone, 1, roleId));
                            Toast.makeText(getContext(), "Create user successful!", Toast.LENGTH_LONG).show();
                        } else if (objectResponse.getError() != null) {
                            Toast.makeText(getContext(), objectResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    manageUserViewModel.updateUser(user.getUserId(), new RequestDTO.UpdateAccountDTO(userName, password, phone, UserRole.values()[roleId])).observe(getViewLifecycleOwner(), objectResponse -> {
                        if (objectResponse != null && objectResponse.isSuccess()) {
                            user.setUserName(userName);
                            user.setPassword(password);
                            user.setPhone(phone);
                            user.setRole(roleId);
                            Toast.makeText(getContext(), "Update user successful!", Toast.LENGTH_LONG).show();
                        } else if (objectResponse.getError() != null) {
                            Toast.makeText(getContext(), objectResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

                userAdapter.notifyDataSetChanged();
                dialog.dismiss();
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        dialog.show();
    }
}

