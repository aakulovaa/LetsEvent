package com.aakulova.letsevent.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.api.ChatApiService;
import com.aakulova.letsevent.api.RetrofitClient;
import com.aakulova.letsevent.api.UserApiService;
import com.aakulova.letsevent.event.SavedActivity;
import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.models.Chat;
import com.aakulova.letsevent.models.User;
import com.aakulova.letsevent.news.NewsActivity;
import com.aakulova.letsevent.user.ProfileActivity;
import com.aakulova.letsevent.user.users_list.UserAdapter;
import com.aakulova.letsevent.user.users_list.UsersActivity;
import com.aakulova.letsevent.user.users_list.UsersListData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        ChatApiService chatApiService = RetrofitClient.getInstance().create(ChatApiService.class);

        Call<List<Chat>> callChat = chatApiService.getChats();
        /**
         * Метод для получения элементов бд таблицы чатов
         */
        callChat.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Chat> chat = response.body();
                    for (int i = 0; i < chat.size(); i++) {
                        //TODO: нет данных отправителя в get-запросе
                        dialogList.add(new DialogListData("Ann",chat.get(i).getMessageChat(), chat.get(i).getSendAt(), R.drawable.le));
                    }
                    DialogAdapter adapter = new DialogAdapter(ChatActivity.this, dialogList);
                    dialogView.setAdapter(adapter);
                } else {
                    Toast.makeText(ChatActivity.this, "Failed to load users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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