package com.aakulova.letsevent.user.users_list;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.api.EventApiService;
import com.aakulova.letsevent.api.RetrofitClient;
import com.aakulova.letsevent.api.UserApiService;
import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.event.ListData;
import com.aakulova.letsevent.models.Event;
import com.aakulova.letsevent.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView usersView = findViewById(R.id.usersListView);
        ArrayList<UsersListData> usersListData = new ArrayList<>();

//        usersListData.add(new UsersListData("admin", R.drawable.le));
//        usersListData.add(new UsersListData("user1", R.drawable.le));
//        usersListData.add(new UsersListData("user2", R.drawable.le));

        UserApiService userApiService = RetrofitClient.getInstance().create(UserApiService.class);

        Call<List<User>> callUser = userApiService.getUsers();
        /**
         * Метод для получения элементов бд таблицы пользователей
         */
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> user = response.body();
                    for (int i = 0; i < user.size(); i++) {
                        usersListData.add(new UsersListData(user.get(i).getLoginUser(), R.drawable.le));
                    }
                    UserAdapter adapter = new UserAdapter(UsersActivity.this, usersListData);
                    usersView.setAdapter(adapter);
                } else {
                    Toast.makeText(UsersActivity.this, "Failed to load users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(UsersActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}