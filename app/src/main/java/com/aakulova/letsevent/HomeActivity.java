package com.aakulova.letsevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private final String[] city = {"Москва", "Воронеж", "Орел"};

    private int[] eventImage = {R.drawable.le,R.drawable.le,R.drawable.le};
    private int goToEvent = R.drawable.arrow_right;
    private String[] eventName = {"BLACK STAR PARTY", "выставка", "арт-встреча"};
    private String[] eventDate = {"28 сентября 11:00", "28 сентября 11:00", "28 сентября 11:00"};

    private ListView eventsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.city_complete_txt);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, city);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener(((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(getApplicationContext(), "Item: "+item, Toast.LENGTH_SHORT).show();
        }));

        eventsView = findViewById(R.id.eventsListView);
        CustomEventAdapter customEventAdapter = new CustomEventAdapter(getApplicationContext(),eventImage, eventName, eventDate, goToEvent);
        eventsView.setAdapter(customEventAdapter);

    }

    public void goToNotices(View v) {
        Intent intent = new Intent(this, NoticesActivity.class);
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


    public void goToChat(View v) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void goToProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

}