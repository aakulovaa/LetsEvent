package com.aakulova.letsevent;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.user.ChatActivity;
import com.aakulova.letsevent.user.NewsActivity;
import com.aakulova.letsevent.user.ProfileActivity;

public class SavedActivity extends AppCompatActivity {

    private int[] eventImage = {R.drawable.le,R.drawable.le,R.drawable.le};
    private int goToEvent = R.drawable.arrow_right;
    private String[] eventName = {"BLACK STAR PARTY", "выставка", "арт-встреча"};
    private String[] eventDate = {"28 сентября 11:00", "28 сентября 11:00", "28 сентября 11:00"};

//    private ListView savedEventsView;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_saved);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//
//        savedEventsView = findViewById(R.id.list_item);
//        CustomEventAdapter customEventAdapter = new CustomEventAdapter(getApplicationContext(),eventImage, eventName, eventDate, goToEvent);
//        savedEventsView.setAdapter(customEventAdapter);
//    }

    public void goToNews(View v) {
        Intent intent = new Intent(this, NewsActivity.class);
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