package com.example.messenger.ui.chat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.messenger.R;
import com.example.messenger.databinding.ActivityAddMemberBinding;

public class AddMemberActivity extends AppCompatActivity {
    ActivityAddMemberBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}