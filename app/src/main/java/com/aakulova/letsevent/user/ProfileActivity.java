package com.aakulova.letsevent.user;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.event.AttendedEventsActivity;
import com.aakulova.letsevent.event.PublicizeEventActivity;
import com.aakulova.letsevent.event.PublishedEventActivity;
import com.aakulova.letsevent.R;
import com.aakulova.letsevent.event.SavedActivity;
import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.models.User;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private User currentUser; // Текущий пользователь

    Dialog dialog;
    Button logOut;
    private TextView attandedTextView;
    private TextView publishedTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentUser = UserSession.getInstance().getCurrentUser();

        TextView logoTextView = findViewById(R.id.user_logo);
        logoTextView.setText(currentUser.getUsername());
        TextView emailTextView = findViewById(R.id.emailTextView);
        emailTextView.setText(currentUser.getEmail());
        ImageView profileImage = findViewById(R.id.user_photo);

        TextView accountTypeTextView = findViewById(R.id.accountTypeTextView);

        publishedTextView = findViewById(R.id.count_events_publish);
        Button publishedBtn = findViewById(R.id.publishedEventBtn);
        Button publishEventBtn = findViewById(R.id.publishEventBtn);

        if (Objects.equals(currentUser.getAccountType(), "regular")) {
            accountTypeTextView.setText("Личный аккаунт");

            publishedTextView.setVisibility(View.GONE); // Скрываем
            publishedBtn.setVisibility(View.GONE); // Скрываем кнопку
            publishedBtn.setEnabled(false); // Деактивируем кнопку
            publishEventBtn.setVisibility(View.GONE); // Скрываем кнопку
            publishEventBtn.setEnabled(false); // Деактивируем кнопку
        } else {
            accountTypeTextView.setText("Бизнес аккаунт");

            publishedTextView.setVisibility(View.VISIBLE); // Делаем видимой
            updatePublishedEventsCount();
            publishedBtn.setVisibility(View.VISIBLE); // Делаем кнопку видимой
            publishedBtn.setEnabled(true); // Активируем кнопку
            publishEventBtn.setVisibility(View.VISIBLE); // Делаем кнопку видимой
            publishEventBtn.setEnabled(true); // Активируем кнопку
        }

        publishedBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, PublishedEventActivity.class);
            startActivity(intent);
        });

        publishEventBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, PublicizeEventActivity.class);
            startActivity(intent);
        });

        TextView followersTextView = findViewById(R.id.subscriber);
        followersTextView.setText(currentUser.getFollowersCount() + " подписчиков");
        TextView followingTextView = findViewById(R.id.subscription);
        followingTextView.setText(currentUser.getFollowingCount() + " подписок");
        attandedTextView = findViewById(R.id.count_events_attended);
        updateAttendedEventsCount();

        logOut = findViewById(R.id.exit);
        dialog = new Dialog(ProfileActivity.this);

        logOut.setOnClickListener(view -> showLogOutDialog());

    }

    @SuppressLint("SetTextI18n")
    private void updateAttendedEventsCount() {
        attandedTextView.setText(currentUser.getAttendedEventsCount() + " посещенных мероприятий");
    }

    @SuppressLint("SetTextI18n")
    private void updatePublishedEventsCount() {
        publishedTextView.setText(currentUser.getPublishedEventsCount() + " опубликованных мероприятий");
    }


    private void showLogOutDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_logout);

        Button logoutBtn = dialog.findViewById(R.id.logout_btn);
        Button cancelBtn = dialog.findViewById(R.id.cancel_button);

        logoutBtn.setOnClickListener(v -> {
            // Логика выхода из аккаунта
            dialog.dismiss();
            Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(ProfileActivity.this, "Вы вышли из аккаунта!", Toast.LENGTH_SHORT).show();
        });

        cancelBtn.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            updateAttendedEventsCount(); // Обновление количества посещенных событий
            updatePublishedEventsCount();
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

    public void goToAttended(View v) {
        Intent intent = new Intent(this, AttendedEventsActivity.class);
        startActivity(intent);
    }

    public void goToSettings(View v) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}