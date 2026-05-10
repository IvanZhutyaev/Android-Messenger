package com.example.messenger.ui.chat;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.model.MessageResponse;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_OUT=1;
    private static final int TYPE_IN=2;
    private final List<MessageResponse> items;
    private final int myUserId;
    public MessageAdapter(List<MessageResponse> items, int myUserId) {
        this.items = items;
        this.myUserId = myUserId;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class OutVH extends RecyclerView.ViewHolder{
        public OutVH(@NonNull View itemView) {
            super(itemView);
        }
    }
    static class InVH extends RecyclerView.ViewHolder{
        public InVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
