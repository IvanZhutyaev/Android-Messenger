package com.example.messenger.ui.chat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.messenger.R;
import com.example.messenger.model.ChatsResponse;
import com.example.messenger.model.UserResponse;
import com.example.messenger.network.ApiClient;
import com.example.messenger.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMemberActivity extends AppCompatActivity {

    private androidx.recyclerview.widget.RecyclerView recyclerUsers;
    private android.widget.EditText edtSearch;

    private ApiService apiService;
    private UserAdapter adapter;

    private ChatsResponse chat;
    private List<UserResponse> allUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        chat = getIntent().getParcelableExtra("chat", ChatsResponse.class);

        recyclerUsers = findViewById(R.id.recyclerUsers);
        edtSearch = findViewById(R.id.edtSearch);

        apiService = ApiClient.getClient().create(ApiService.class);

        recyclerUsers.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter(new ArrayList<>(), user -> addUserToChat(user));
        recyclerUsers.setAdapter(adapter);

        initSearch();
        loadUsers();
    }

    private void loadUsers() {
        apiService.listUsers().enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call,
                                   Response<List<UserResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allUsers = response.body();
                    adapter.updateList(allUsers);
                } else {
                    Toast.makeText(AddMemberActivity.this,
                            "Ошибка загрузки пользователей",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Toast.makeText(AddMemberActivity.this,
                        "Ошибка сети: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSearch() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterUsers(s.toString());
            }

           @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterUsers(String query) {
        query = query.toLowerCase().trim();

        List<UserResponse> filtered = new ArrayList<>();

        for (UserResponse user : allUsers) {
            String login = user.getLogin() == null ? "" : user.getLogin().toLowerCase();
            String firstName = user.getFirst_name() == null ? "" : user.getFirst_name().toLowerCase();
            String lastName = user.getLast_name() == null ? "" : user.getLast_name().toLowerCase();

            if (login.contains(query)
                    || firstName.contains(query)
                    || lastName.contains(query)) {
                filtered.add(user);
            }
        }

        adapter.updateList(filtered);
    }

    private void addUserToChat(UserResponse user) {
        if (chat == null) {
            Toast.makeText(this, "Чат не найден", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService.addMember(chat.getChat_id(), user.getUser_id())


                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AddMemberActivity.this,
                                    "Пользователь добавлен",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddMemberActivity.this,
                                    "Не удалось добавить пользователя",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AddMemberActivity.this,
                                "Ошибка сети: " + t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}




