package com.example.messenger.ui.chat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
{
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

  @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

    }

   @Override
    public int getItemCount() {
        return 0;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtLogin;

        public UserViewHolder(@NonNull View itemView, TextView txtName, TextView txtLogin) {
            super(itemView);
            this.txtName = txtName;
            this.txtLogin = txtLogin;
        }
    }
}