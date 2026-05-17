package com.example.messenger.ui.chat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.model.UserResponse;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
{
    public interface OnUserClickListener{
        void onUserClick(UserResponse user);

    }
    private List<UserResponse> users;
    private OnUserClickListener listener;

    public UserAdapter(OnUserClickListener listener, List<UserResponse> users) {
        this.listener = listener;
        this.users = users;
    }
    public void updateList(List<UserResponse> newUsers){
        users=newUsers;
        notifyDataSetChanged();
    }
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