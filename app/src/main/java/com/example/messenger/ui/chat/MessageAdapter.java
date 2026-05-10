package com.example.messenger.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.databinding.ItemMessageInBinding;
import com.example.messenger.databinding.ItemMessageOutBinding;
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

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getSender_id()==myUserId?TYPE_OUT:TYPE_IN;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_OUT){
            return new OutVH(ItemMessageOutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
        }
        return new InVH(ItemMessageInBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class OutVH extends RecyclerView.ViewHolder{
        ItemMessageOutBinding binding;
        public OutVH(ItemMessageOutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
    static class InVH extends RecyclerView.ViewHolder{
        ItemMessageInBinding binding;
        public InVH(ItemMessageInBinding binding ){
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
