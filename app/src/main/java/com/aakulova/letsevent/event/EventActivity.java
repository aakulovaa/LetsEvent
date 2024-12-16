package com.aakulova.letsevent.event;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.user.ChatActivity;
import com.aakulova.letsevent.user.NewsActivity;
import com.aakulova.letsevent.user.ProfileActivity;
import com.aakulova.letsevent.databinding.ActivityEventBinding;
import com.aakulova.letsevent.models.User;
import com.aakulova.letsevent.user.UserSession;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;
    private static final ArrayList<ListData> attendedEvents = new ArrayList<>();
    private static final ArrayList<ListData> savedEvents = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        if (intent != null) {
            String name = intent.getStringExtra("name");
            String date = intent.getStringExtra("date");
            String desc = intent.getStringExtra("desc");
            String addr = intent.getStringExtra("addr");
            int countPeople = intent.getIntExtra("countPeople", 0);
            int image = intent.getIntExtra("image", R.drawable.le);

            binding.eventName.setText(name);
            binding.dateEvent.setText(date);
            binding.descriptionEvent.setText(desc);
            binding.addressEvent.setText(addr);
            binding.countPeople.setText(String.valueOf(countPeople));
            binding.eventImage.setImageResource(image);
        }
        binding.btnGoToTheEvent.setOnClickListener(v -> toggleAttendance());

        binding.btnFoeSaving.setOnClickListener(v -> {
            ListData savedEvent = new ListData(
                    binding.eventName.getText().toString(),
                    binding.dateEvent.getText().toString(),
                    binding.descriptionEvent.getText().toString(),
                    binding.addressEvent.getText().toString(),
                    Integer.parseInt(binding.countPeople.getText().toString()),
                    R.drawable.le
            );

            if (savedEvents.contains(savedEvent)) {
                savedEvents.remove(savedEvent);
                Toast.makeText(this, "Событие удалено из избранного.", Toast.LENGTH_SHORT).show();
            } else {
                savedEvents.add(savedEvent);
                Toast.makeText(this, "Событие добавлено в избранное.", Toast.LENGTH_SHORT).show();
            }

        });

        //assert intent != null;
        //String authorId = intent.getStringExtra("authorId"); // Получаем идентификатор автора

        //реализовать возможность редактировать только опубликованное пользователем
        //eventEdit();
    }


//    private void eventEdit() {
//        User currentUser = UserSession.getInstance().getCurrentUser();
//// Проверяем, является ли текущий пользователь автором мероприятия
//        //if (Objects.equals(currentUser.getId(), authorId)) {
//            // Разрешаем редактирование
//            Button editEventBtn = findViewById(R.id.editEvent);
//
//            if (Objects.equals(currentUser.getAccountType(), "regular")) {
//                editEventBtn.setVisibility(View.GONE); // Скрываем кнопку
//                editEventBtn.setEnabled(false); // Деактивируем кнопку
//            } else {
//                editEventBtn.setVisibility(View.VISIBLE); // Делаем кнопку видимой
//                editEventBtn.setEnabled(true); // Активируем кнопку
//            }
//        //}


//        editEventBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(this, PublishedEventActivity.class);
//            startActivity(intent);
//        });
    //}

    private void toggleAttendance() {
        User currentUser = UserSession.getInstance().getCurrentUser();

        ListData attendedEvent = new ListData(
                binding.eventName.getText().toString(),
                binding.dateEvent.getText().toString(),
                binding.descriptionEvent.getText().toString(),
                binding.addressEvent.getText().toString(),
                Integer.parseInt(binding.countPeople.getText().toString()),
                R.drawable.le
        );

        if (attendedEvents.contains(attendedEvent)) {
            currentUser.decrementAttendedEventsCount();
            attendedEvents.remove(attendedEvent);
            Toast.makeText(this, "Событие удалено из списка посещенных.", Toast.LENGTH_SHORT).show();
        } else {
            currentUser.incrementAttendedEventsCount();
            attendedEvents.add(attendedEvent);
            Toast.makeText(this, "Событие добавлено в список посещенных.", Toast.LENGTH_SHORT).show();
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra("updateCount", true);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public static ArrayList<ListData> getAttendedEvents() {
        return attendedEvents;
    }

    public static ArrayList<ListData> getSavedEvents() {
        return savedEvents;
    }

    public void goToBack(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
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

    public void goToHome(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}