package com.aakulova.letsevent.user.users_list;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.R;

import java.util.ArrayList;

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

        usersListData.add(new UsersListData("Новое сообщение", R.drawable.le));
        usersListData.add(new UsersListData("Обновление приложения", R.drawable.le));
        usersListData.add(new UsersListData("Напоминание о событии", R.drawable.le));

        UserAdapter adapter = new UserAdapter(this, usersListData);
        usersView.setAdapter(adapter);

    }
}