package com.example.messenger.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.messenger.R;
import com.example.messenger.databinding.ActivityChatCreateBinding;
import com.example.messenger.model.ChatCreateRequest;
import com.example.messenger.model.ChatsResponse;
import com.example.messenger.network.ApiClient;
import com.example.messenger.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatCreateActivity extends AppCompatActivity {
    private ApiService apiService;
    ActivityChatCreateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiService = ApiClient.getClient().create(ApiService.class);

        binding.btnCreateChat.setOnClickListener(v->createChat());

    }

    private void createChat() {
        String chatName=binding.edtChatName.getText().toString();
        boolean isGroup = binding.checkGroup.isChecked();
        if(chatName.isEmpty()){
            Toast.makeText(this, "Введите название чата", Toast.LENGTH_LONG).show();
        }
        ChatCreateRequest request = new ChatCreateRequest(chatName, isGroup);
        apiService.createChat(request).enqueue(new Callback<ChatsResponse>() {
            @Override
            public void onResponse(Call<ChatsResponse> call, Response<ChatsResponse> response) {
                if(response.isSuccessful()&& response.body()!=null){
                    ChatsResponse chat=response.body();
                    Intent intent =new Intent(ChatCreateActivity.this, AddMemberActivity.class);
                    intent.putExtra("chat", chat);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(ChatCreateActivity.this, "Ошибка создания чата", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ChatsResponse> call, Throwable t) {
                Toast.makeText(ChatCreateActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}