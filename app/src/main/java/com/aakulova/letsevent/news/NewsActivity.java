package com.aakulova.letsevent.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.api.ChatApiService;
import com.aakulova.letsevent.api.NewsApiService;
import com.aakulova.letsevent.api.RetrofitClient;
import com.aakulova.letsevent.chat.ChatActivity;
import com.aakulova.letsevent.chat.DialogAdapter;
import com.aakulova.letsevent.chat.DialogListData;
import com.aakulova.letsevent.event.SavedActivity;
import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.models.Chat;
import com.aakulova.letsevent.models.News;
import com.aakulova.letsevent.models.User;
import com.aakulova.letsevent.user.ProfileActivity;
import com.aakulova.letsevent.user.UserSession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;

    ImageButton imageBtn, sendBtn;
    private EditText editText;
    private User currentUser = UserSession.getInstance().getCurrentUser();
    private ArrayList<NewsListData> newsList;
    private NewsAdapter newsAdapter;
    private int imageNews; // хранит ресурс изображения новости
    private Uri imageUri; // хранит URI изображения

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageBtn = findViewById(R.id.ico_image);
        sendBtn = findViewById(R.id.ico_send_btn);
        editText = findViewById(R.id.editNews);


        newsAdapter = new NewsAdapter(this, newsList);

        ListView listView = findViewById(R.id.newsListView);
        newsList = new ArrayList<>();

        NewsApiService newsApiService = RetrofitClient.getInstance().create(NewsApiService.class);

        Call<List<News>> callNews = newsApiService.getNews();
        /**
         * Метод для получения элементов бд таблицы новостей
         */
        callNews.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<News> news = response.body();
                    for (int i = 0; i < news.size(); i++) {
                        newsList.add(new NewsListData("Ann",news.get(i).getTitleNews(), news.get(i).getDescNews(), R.drawable.le,R.drawable.le));
                    }
                    NewsAdapter adapter = new NewsAdapter(NewsActivity.this, newsList);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(NewsActivity.this, "Failed to load news", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        sendBtn.setOnClickListener(view -> publishNews());
        imageBtn.setOnClickListener(view -> openGallery());
    }

    private void openGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
        }
    }

    private void publishNews() {
        String inputText = editText.getText().toString().trim();
        if (!inputText.isEmpty()) {
            String[] parts = inputText.split("\n", 2);
            String nameNews = parts[0]; // Первую строку берем как название
            String descNews = parts.length > 1 ? parts[1] : ""; // Остальное как описание

            int imageResource = imageUri != null ? R.drawable.le : R.drawable.le;

            newsList.add(new NewsListData(currentUser.getLoginUser(), nameNews, descNews, R.drawable.le, imageResource));
            newsAdapter.notifyDataSetChanged();
            editText.setText("");
            imageUri = null; // сбросить URI после публикации
        }
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
}