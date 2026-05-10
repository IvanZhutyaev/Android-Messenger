package com.example.messenger.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.messenger.R;
import com.example.messenger.databinding.ActivityChatListBinding;
import com.example.messenger.model.ChatsResponse;
import com.example.messenger.network.ApiClient;
import com.example.messenger.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatListActivity extends AppCompatActivity {
    private ActivityChatListBinding binding;
    private ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        api= ApiClient.getClient().create(ApiService.class);

        binding.recyclerChats.setLayoutManager(new LinearLayoutManager(this));
        loadChats();
    }

    private void loadChats() {
        api.getChats().enqueue(new Callback<List<ChatsResponse>>() {
            @Override
            public void onResponse(Call<List<ChatsResponse>> call, Response<List<ChatsResponse>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    ChatAdapter adapter = new ChatAdapter(response.body(), chat -> {
                        Intent intent = new Intent(ChatListActivity.this, ChatMessagesActivity.class);
                        intent.putExtra("chat", chat);
                        startActivity(intent);
                    });
                    binding.recyclerChats.setAdapter(adapter);
                } else{
                    Toast.makeText(ChatListActivity.this, "Не удалось загрузить чаты", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ChatsResponse>> call, Throwable t) {
                Toast.makeText(ChatListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}