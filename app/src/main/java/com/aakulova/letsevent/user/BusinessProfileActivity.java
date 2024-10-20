package com.aakulova.letsevent.user;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aakulova.letsevent.R;

public class BusinessProfileActivity extends AppCompatActivity {

    private User currentUser; // Текущий пользователь

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile);

        // Получаем текущего пользователя (здесь это может быть загружено из базы данных)
        currentUser = new User("1", "Ann","user@example.com","", "business");

        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView accountTypeTextView = findViewById(R.id.accountTypeTextView);

        emailTextView.setText(currentUser.getEmail());
        accountTypeTextView.setText("Account Type: " + currentUser.getAccountType());
        // Здесь добавьте дополнительные возможности для бизнес-профиля
    }

}