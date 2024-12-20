package com.aakulova.letsevent.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.chat.ChatActivity;
import com.aakulova.letsevent.news.NewsActivity;
import com.aakulova.letsevent.user.ProfileActivity;

import java.util.ArrayList;

public class AttendedEventsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attended_events);

        ListView attendedEventsView = findViewById(R.id.attendedEventsListView);
        ArrayList<ListData> attendedEvents = EventActivity.getAttendedEvents();

        ListAdapter listAdapter = new ListAdapter(this, attendedEvents);
        attendedEventsView.setAdapter(listAdapter);
    }

    public void goToBack(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
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

    public void goToChat(View v) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void goToAttended(View v) {
        Intent intent = new Intent(this, AttendedEventsActivity.class);
        startActivity(intent);
    }

    public void goToProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}