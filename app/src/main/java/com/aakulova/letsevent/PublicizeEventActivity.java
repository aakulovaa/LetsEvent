package com.aakulova.letsevent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.databinding.ActivityEventBinding;
import com.aakulova.letsevent.databinding.ActivityPublicizeEventBinding;
import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.event.ListData;
import com.aakulova.letsevent.user.ChatActivity;
import com.aakulova.letsevent.user.NewsActivity;
import com.aakulova.letsevent.user.ProfileActivity;
import com.aakulova.letsevent.user.User;
import com.aakulova.letsevent.user.UserSession;

import java.util.ArrayList;

public class PublicizeEventActivity extends AppCompatActivity {

    ActivityPublicizeEventBinding binding;
    private static final ArrayList<ListData> publishedEvents = new ArrayList<>();


    private ImageView eventImageView;
    private Uri eventImageUrl;
    private final String[] city = {"Москва", "Воронеж", "Орел"};
    private final String[] categpry = {"Выставка", "Мастер-класс", "Стендап"};


    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_publicize_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.city_complete_txt);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, city);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            String selectedCity = adapterView.getItemAtPosition(i).toString();
        });

        AutoCompleteTextView categoryAutoCompleteTextView = findViewById(R.id.category_complete_txt);
        ArrayAdapter<String> categoryArrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, categpry);
        categoryAutoCompleteTextView.setAdapter(categoryArrayAdapter);
        categoryAutoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            String selectedCategory = adapterView.getItemAtPosition(i).toString();
        });

        eventImageView = findViewById(R.id.eventImageView);

        // Запуск выбора изображения
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                eventImageUrl = result.getData().getData();
                eventImageView.setImageURI(eventImageUrl);
            }
        });

        eventImageView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });

        Button publishEventBtn = findViewById(R.id.publishEventBtn);
        publishEventBtn.setOnClickListener(v -> {
            User currentUser = UserSession.getInstance().getCurrentUser();
            currentUser.incrementPublishedEventsCount();
            Toast.makeText(this, "Событие добавлено в список опубликованных.", Toast.LENGTH_SHORT).show();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("updateCount", true);
            setResult(RESULT_OK, returnIntent);
            finish();
        });

    }


    public static ArrayList<ListData> getPublishedEvents() {
        return publishedEvents;
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