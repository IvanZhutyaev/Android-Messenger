package com.example.messenger.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messenger.R;
import com.example.messenger.model.ChatsResponse;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>

{
    public interface OnChatClickListner{
        void onChatClick(ChatsResponse chat);
    }
    private final List<ChatsResponse> chats;

    private final OnChatClickListner listner;

    public ChatAdapter(List<ChatsResponse> chats, OnChatClickListner listner) {
        this.chats = chats;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatsResponse chat = chats.get(position);

        holder.textChatName.setText(
                chat.getChat_name() == null ? "Без названия" : chat.getChat_name()
        );
        holder.textChatType.setText(
                chat.isIs_group_chat() ? "Групповой чат" : "Личный чат"
        );
        holder.itemView.setOnClickListener(v -> {
            if (listner != null){
                listner.onChatClick(chat);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder{

        TextView textChatName;
        TextView textChatType;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            textChatName = itemView.findViewById(R.id.txtChatName);
            textChatType = itemView.findViewById(R.id.txtChatType);
        }
    }
}