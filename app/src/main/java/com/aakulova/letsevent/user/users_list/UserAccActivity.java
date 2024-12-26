package com.aakulova.letsevent.user.users_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.api.RetrofitClient;
import com.aakulova.letsevent.api.UserApiService;
import com.aakulova.letsevent.chat.ChatActivity;
import com.aakulova.letsevent.chat.DialogActivity;
import com.aakulova.letsevent.databinding.ActivityEventBinding;
import com.aakulova.letsevent.databinding.ActivityUserAccBinding;
import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.event.SavedActivity;
import com.aakulova.letsevent.models.User;
import com.aakulova.letsevent.news.NewsActivity;
import com.aakulova.letsevent.user.ProfileActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAccActivity extends AppCompatActivity {
    ActivityUserAccBinding binding;
    User user;
    private TextView attandedTextView;
    private TextView publishedTextView;
    TextView followersTextView;
    TextView followingTextView;
    private boolean isSubscribed = false; // Флаг, указывающий на подписку пользователя
    private Button subscribeButton;


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

            subscribeButton = findViewById(R.id.subscribe);

            UserApiService userApiService = RetrofitClient.getInstance().create(UserApiService.class);

            Call<User> callUser = userApiService.findByLogin(name);
            /**
             * Метод для получения элементов бд таблицы пользователей
             */
            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Если запрос успешный, получаем данные пользователя
                        user = response.body();
                        followersTextView = findViewById(R.id.subscriber);
                        followersTextView.setText(user.getFollowersCount()+ " подписчиков");
                        followingTextView = findViewById(R.id.subscription);
                        followingTextView.setText(user.getFollowingCount()+ " подписок");
                        attandedTextView = findViewById(R.id.count_events_attended);
                        attandedTextView.setText(user.getAttendedEventsCount()+" посещенных мероприятий");
                        publishedTextView = findViewById(R.id.count_events_publish);
                        publishedTextView.setText(user.getPublishedEventsCount()+" опубликованных мероприятий");
                    } else {
                        // Если данные не получены
                        Toast.makeText(UserAccActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Ошибка при выполнении запроса
                    Toast.makeText(UserAccActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public void addFolover(View v) {
        int countOfFolowers = user.getFollowersCount();
        //TODO: обновление данных в бд и получение их в качестве количества подписчиков
        if (isSubscribed) {
            user.setFollowersCount(countOfFolowers-1);
            countOfFolowers = user.getFollowersCount();
            followersTextView.setText(countOfFolowers  + " подписчиков");
            Toast.makeText(UserAccActivity.this, "Вы отписаны от " + user.getLoginUser(), Toast.LENGTH_SHORT).show();
            isSubscribed=false;
            subscribeButton.setText("Подписаться");
        }else {
            user.setFollowersCount(countOfFolowers+1);
            countOfFolowers = user.getFollowersCount();
            followersTextView.setText(countOfFolowers + " подписчиков");
            Toast.makeText(UserAccActivity.this, "Вы подписаны на " + user.getLoginUser(), Toast.LENGTH_SHORT).show();
            isSubscribed=true;
            subscribeButton.setText("Отписаться");
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