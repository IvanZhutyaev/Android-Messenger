package com.example.messenger.ui.chat;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
import com.example.messenger.model.MessageCreateRequest;
import com.example.messenger.model.MessageResponse;
import com.example.messenger.network.ApiClient;
import com.example.messenger.network.ApiService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        ws.connect(session.getUserId(), new WebSocketListener() {
            @Override
            public void onOpen(@NonNull WebSocket webSocket, @NonNull okhttp3.Response response) {
                super.onOpen(webSocket, response);
            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
                runOnUiThread(()->{
                    try {
                        JSONObject json = new JSONObject(text);
                        String event = json.optString(text);
                        if ("new_message".equals(event)){
                            loadMessage();
                        }
                    }
                    catch (Exception ignored){

                    }


                });
            }
        });

    }

    private void loadMessage() {
        if(chat == null)return;
        api.getMessages(chat.getChat_id()).enqueue(new Callback<List<MessageResponse>>() {
            @Override
            public void onResponse(Call<List<MessageResponse>> call, Response<List<MessageResponse>> response) {
                if(response.isSuccessful()&& response.body()!=null){
                    items.clear();
                    items.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<MessageResponse>> call, Throwable t) {
                Toast.makeText(ChatMessagesActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void sendMessage() {
        if(chat == null)return;
        String text = binding.edtMessage.getText().toString().trim();
        if(text.isEmpty())return;
        api.sendMessage(chat.getChat_id(), new MessageCreateRequest(session.getUserId(),chat.getChat_id(), text)).enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                binding.edtMessage.setText("");
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(ChatMessagesActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ws.close();
    }
}