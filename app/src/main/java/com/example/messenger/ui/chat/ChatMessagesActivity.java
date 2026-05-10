package com.example.messenger.ui.chat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.messenger.R;
import com.example.messenger.data.SessionManager;
import com.example.messenger.data.WebSocketManager;
import com.example.messenger.databinding.ActivityChatMessagesBinding;
import com.example.messenger.model.ChatsResponse;
import com.example.messenger.model.MessageResponse;
import com.example.messenger.network.ApiClient;
import com.example.messenger.network.ApiService;

import java.util.ArrayList;
import java.util.List;

public class ChatMessagesActivity extends AppCompatActivity {
    ActivityChatMessagesBinding binding;
    private ApiService api;
    private ChatsResponse chat;
    private SessionManager session;
    private MessageAdapter adapter;
    private final List<MessageResponse> items = new ArrayList<>();
    private final WebSocketManager ws = new WebSocketManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatMessagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chat=getIntent().getParcelableExtra("chat", ChatsResponse.class);
        session=new SessionManager(this);
        api = ApiClient.getClient().create(ApiService.class);

        binding.txtTitle.setText(chat!=null?chat.getChat_name():"Чат");
        adapter = new MessageAdapter(items, session.getUserId());
        binding.recyclerMessages.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerMessages.setAdapter(adapter);

        binding.btnSend.setOnClickListener(v->sendMessage());
        loadMessage();
        connectSocket();
    }

    private void connectSocket() {
    }

    private void loadMessage() {
    }

    private void sendMessage() {
    }
}