package com.prmproject.hairsalonbooking.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prmproject.hairsalonbooking.R;
import com.prmproject.hairsalonbooking.data.model.HairService;
import com.prmproject.hairsalonbooking.data.model.User;
import com.prmproject.hairsalonbooking.data.model.type.UserStatus;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private OnUserClickListener onUserClickListener;
    private final OnChangeStatusClickListener onChangeStatusClickListener;

    public interface OnUserClickListener {
        void onEditUser(User user);
    }

    public UserAdapter(List<User> userList, OnUserClickListener onUserClickListener, OnChangeStatusClickListener onChangeStatusClickListener) {
        this.userList = userList;
        this.onUserClickListener = onUserClickListener;
        this.onChangeStatusClickListener = onChangeStatusClickListener;
    }

    public interface OnChangeStatusClickListener {
        void onChangeStatusClick(User user);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewUserName.setText(user.getUserName());

        holder.buttonStatus.setImageResource((user.getStatusInt() != null && user.getStatusInt() == UserStatus.Active.ordinal())
                ? android.R.drawable.ic_menu_delete
                : android.R.drawable.ic_menu_revert);

        holder.buttonEdit.setOnClickListener(v -> onUserClickListener.onEditUser(user));
        holder.buttonStatus.setOnClickListener(v -> {
            int newStatus = (user.getStatusInt() == UserStatus.Active.ordinal()) ? UserStatus.Banned.ordinal() : UserStatus.Active.ordinal();
            user.setStatus(newStatus);
            holder.buttonStatus.setImageResource(user.getStatusInt() != null && user.getStatus() == UserStatus.Active
                    ? android.R.drawable.ic_menu_delete
                    : android.R.drawable.ic_menu_revert);
            onChangeStatusClickListener.onChangeStatusClick(user);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName;
        ImageButton buttonEdit, buttonStatus;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textView_userName);
            buttonEdit = itemView.findViewById(R.id.button_edit);
            buttonStatus = itemView.findViewById(R.id.buttonStatus);
        }
    }
}
