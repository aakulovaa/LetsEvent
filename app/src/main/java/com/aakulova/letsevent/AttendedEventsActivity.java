package com.aakulova.letsevent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AttendedEventsActivity extends AppCompatActivity {

    private int[] eventImage = {R.drawable.le,R.drawable.le,R.drawable.le};
    private int goToEvent = R.drawable.arrow_right;
    private String[] eventName = {"BLACK STAR PARTY", "выставка", "арт-встреча"};
    private String[] eventDate = {"28 сентября 11:00", "28 сентября 11:00", "28 сентября 11:00"};

    private ListView attendedEventsView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_attended_events);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        attendedEventsView = findViewById(R.id.list_item);
        CustomEventAdapter customEventAdapter = new CustomEventAdapter(getApplicationContext(),eventImage, eventName, eventDate, goToEvent);
        attendedEventsView.setAdapter(customEventAdapter);
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