package com.example.messenger.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.messenger.R;
import com.example.messenger.data.SessionManager;
import com.example.messenger.databinding.ActivityLoginBinding;
import com.example.messenger.model.UserLoginRequest;
import com.example.messenger.model.UserResponse;
import com.example.messenger.network.ApiClient;
import com.example.messenger.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private ApiService api;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    api= ApiClient.getClient().create(ApiService.class);
    session=new SessionManager(this);
    binding.btnLogin.setOnClickListener(v->login());
    binding.txtGoRegister.setOnClickListener(v->startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void login() {
        String login  =binding.edtLogin.getText().toString();
        String password=binding.edtPassword.getText().toString();
        api.login(new UserLoginRequest(login, password)).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()&& response.body()!=null){
                    UserResponse user = response.body();
                    session.saveUser(user.getUser_id(), user.getLogin());
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}