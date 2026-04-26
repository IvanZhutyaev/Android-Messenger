package com.example.messenger.ui.auth;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.messenger.R;
import com.example.messenger.databinding.ActivityRegisterBinding;
import com.example.messenger.model.UserRegisterRequest;
import com.example.messenger.model.UserResponse;
import com.example.messenger.network.ApiClient;
import com.example.messenger.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private ApiService api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        api= ApiClient.getClient().create(ApiService.class);
        binding.btnRegister.setOnClickListener(v->register());
    }

    private void register() {
        String login  =binding.edtLogin.getText().toString();
        String password=binding.edtPassword.getText().toString();
        String firstName  =binding.edtFirstName.getText().toString();
        String lastName=binding.edtLastName.getText().toString();

        api.register(new UserRegisterRequest(login, password, firstName, lastName)).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "УСПЕШНО!!!", Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "ОШИБКА РЕГИСТРАЦИИ АААА!!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}