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
import com.aakulova.letsevent.event.EventActivity;
import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.event.ListAdapter;
import com.aakulova.letsevent.event.ListData;

import java.util.ArrayList;

public class NoticesActivity extends AppCompatActivity {

    private NotificationAdapter adapter;
    private ArrayList<NoticeListData> notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notices);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView noticesView = findViewById(R.id.noticesEventsListView);
        notificationList = new ArrayList<>();

        // Пример добавления уведомлений
        notificationList.add(new NoticeListData("Новое сообщение", "https://example.com/image1.jpg", R.drawable.le));
        notificationList.add(new NoticeListData("Обновление приложения", "https://example.com/image2.jpg", R.drawable.le));
        notificationList.add(new NoticeListData("Напоминание о событии", "https://example.com/image3.jpg", R.drawable.le));

        adapter = new NotificationAdapter(this, notificationList);
        noticesView.setAdapter(adapter);

        //ListView noticesEventsView = findViewById(R.id.noticesEventsListView);
//        ArrayList<ListData> attendedEvents = EventActivity.getSavedEvents();
//
//        ListAdapter listAdapter = new ListAdapter(this, attendedEvents);
//        noticesEventsView.setAdapter(listAdapter);
    }

    public void goToBack(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}