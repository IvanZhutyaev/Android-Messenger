package com.example.messenger.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.R;
import com.example.messenger.model.UserResponse;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public interface OnUserClickListener {
        void onUserCLick(UserResponse user);
    }

    private List<UserResponse> users;
    private OnUserClickListener listener;

    public UserAdapter(List<UserResponse> users, OnUserClickListener listener) {
        this.users = users;
        this.listener = listener;
    }

    public void updateList(List<UserResponse> newUsers) {
        users = newUsers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserResponse user = users.get(position);
        String fullName = user.getFirst_name()+" "+user.getLast_name();
        holder.txtName.setText(fullName);
        holder.txtLogin.setText("@"+user.getLogin());
        holder.itemView.setOnClickListener(view -> {
            if (listener!=null){
                listener.onUserCLick(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtLogin;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtName = itemView.findViewById(R.id.txtFullName);
            this.txtLogin = itemView.findViewById(R.id.txtLogin);
        }
    }
}