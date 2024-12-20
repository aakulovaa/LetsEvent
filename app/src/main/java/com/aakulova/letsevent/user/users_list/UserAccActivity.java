package com.aakulova.letsevent.user.users_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.chat.ChatActivity;
import com.aakulova.letsevent.chat.DialogActivity;
import com.aakulova.letsevent.databinding.ActivityEventBinding;
import com.aakulova.letsevent.databinding.ActivityUserAccBinding;
import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.event.SavedActivity;
import com.aakulova.letsevent.news.NewsActivity;
import com.aakulova.letsevent.user.ProfileActivity;

public class UserAccActivity extends AppCompatActivity {
    ActivityUserAccBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserAccBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int image = intent.getIntExtra("image", R.drawable.le);

            binding.userLogo.setText(name);
            binding.userPhoto.setImageResource(image);
        }
    }

    public void goToNews(View v) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public void goToSaved(View v) {
        Intent intent = new Intent(this, SavedActivity.class);
        startActivity(intent);
    }

    public void goToHome(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void goToChat(View v) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void goToProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goToDialog(View v) {
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }
}