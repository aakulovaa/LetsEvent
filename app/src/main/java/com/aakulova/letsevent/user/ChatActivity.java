package com.aakulova.letsevent.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.event.SavedActivity;
import com.aakulova.letsevent.event.HomeActivity;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView dialogView = findViewById(R.id.dialogsListView);
        ArrayList<DialogListData> dialogList = new ArrayList<>();

        // Пример добавления уведомлений
        dialogList.add(new DialogListData("Новое сообщение", "https://example.com/image1.jpg", R.drawable.le));
        dialogList.add(new DialogListData("Обновление приложения", "https://example.com/image2.jpg", R.drawable.le));
        dialogList.add(new DialogListData("Напоминание о событии", "https://example.com/image3.jpg", R.drawable.le));

        DialogAdapter adapter = new DialogAdapter(this, dialogList);
        dialogView.setAdapter(adapter);
    }

    public void goToUsersList(View v) {
        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
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


    public void goToProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}