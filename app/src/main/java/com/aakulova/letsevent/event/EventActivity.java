package com.aakulova.letsevent.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.SavedActivity;
import com.aakulova.letsevent.user.ChatActivity;
import com.aakulova.letsevent.user.NewsActivity;
import com.aakulova.letsevent.user.ProfileActivity;
import com.aakulova.letsevent.databinding.ActivityEventBinding;

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String date = intent.getStringExtra("date");
            String desc = intent.getStringExtra("desc");
            String addr = intent.getStringExtra("addr");
            int countPeople = intent.getIntExtra("countPeople", 0);
            int image = intent.getIntExtra("image", R.drawable.le);

            binding.eventName.setText(name);
            binding.dateEvent.setText(date);
            binding.descriptionEvent.setText(desc);
            binding.addressEvent.setText(addr);
            binding.countPeople.setText(String.valueOf(countPeople)); // Преобразование в строку
            binding.eventImage.setImageResource(image);
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


    public void goToChat(View v) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void goToProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goToHome(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}