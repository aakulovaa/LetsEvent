package com.aakulova.letsevent.user;

import static com.aakulova.letsevent.R.layout.*;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.aakulova.letsevent.AttendedEventsActivity;
import com.aakulova.letsevent.R;
import com.aakulova.letsevent.SavedActivity;
import com.aakulova.letsevent.event.HomeActivity;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private User currentUser; // Текущий пользователь

    Dialog dialog;
    Button logOut;

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

        // Получаем текущего пользователя (здесь это может быть загружено из базы данных)
        currentUser = new User("1", "User","user@example.com","", "regular");

        TextView logoTextView = findViewById(R.id.user_logo);
        logoTextView.setText(currentUser.getUsername());
        TextView emailTextView = findViewById(R.id.emailTextView);
        emailTextView.setText(currentUser.getEmail());
        ImageView profileImage = findViewById(R.id.user_photo);

        TextView accountTypeTextView = findViewById(R.id.accountTypeTextView);
        if(Objects.equals(currentUser.getAccountType(), "regular")){
            accountTypeTextView.setText("Бизнес аккаунт");
        }
        else {
            accountTypeTextView.setText("Личный аккаунт");
        }
//        Button settingsButton = findViewById(R.id.settingsButton);
//
//
//        settingsButton.setOnClickListener(v -> {
//            Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
//            startActivity(intent);
//        });


        logOut = findViewById(R.id.exit);
        dialog = new Dialog(ProfileActivity.this);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogOutDialog();
            }
        });

    }

    private void showLogOutDialog() {
        dialog.setContentView(R.layout.custom_dialog_logout);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button logout = dialog.findViewById(R.id.logout_btn);
        Button cancel = dialog.findViewById(R.id.cancel_button);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(ProfileActivity.this, "Вы вышли из аккаунта!", Toast.LENGTH_SHORT).show();
            }
        });
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