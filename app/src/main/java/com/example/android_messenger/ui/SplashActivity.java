package com.example.android_messenger.ui;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_messenger.MainActivity;
import com.example.android_messenger.R;
import com.example.android_messenger.data.SessionManager;
import com.example.android_messenger.ui.auth.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        logo=binding.logo;
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        logo.startAnimation(animation);
        SessionManager session = new SessionManager(this);
        new Handler(Looper.getMainLooper()).postDelayed(()->{
            if(session.isLoggedIn())
                startActivity(new Intent(this, MainActivity.class));
            else
                startActivity(new Intent(this, LoginActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        },1200
                );
    }
}