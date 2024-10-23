package com.aakulova.letsevent.event;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.aakulova.letsevent.R;
import com.aakulova.letsevent.SavedActivity;
import com.aakulova.letsevent.user.ChatActivity;
import com.aakulova.letsevent.user.NewsActivity;
import com.aakulova.letsevent.user.NoticesActivity;
import com.aakulova.letsevent.user.ProfileActivity;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    private final String[] city = {"Москва", "Воронеж", "Орел"};
    private final int[] eventImage = {R.drawable.le,R.drawable.le,R.drawable.le};
    private final String[] eventName = {"BLACK STAR PARTY", "выставка", "арт-встреча"};
    private final String[] eventDate = {"28 сентября 11:00", "28 сентября 11:00", "28 сентября 11:00"};
    private final String[] eventDesc = {"BLACK STAR PARTY", "выставка", "арт-встреча"};
    private final String[] eventAddr = {"Москва", "Воронеж", "Орел"};

    private ListView eventsView;

    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    private final ArrayList<ListData> filteredDataArrayList = new ArrayList<>();

    private TextView noEventsTextView; // TextView для сообщения об отсутствии мероприятий

    private boolean isAuthenticated = false; // Переменная для проверки авторизации
    Dialog dialog;
    ImageButton notices, news, saves, home, chat, profile;

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
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            String selectedCity = adapterView.getItemAtPosition(i).toString();
            filterByCity(selectedCity); // Фильтрация по городу
        });

        SearchView searchView = findViewById(R.id.searchEvent);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        /** список мероприятий*/
        for (int i = 0; i < eventName.length; i++) {
            ListData listData = new ListData(eventName[i], eventDate[i], eventDesc[i], eventAddr[i],0, eventImage[i]);
            dataArrayList.add(listData);
        }

//        for (int i = 0; i < eventName.length; i++) {
//            ListData listData = new ListData(eventName[i], eventDate[i], eventDesc[i], eventAddr[i], 0, eventImage[i], "1"); // Пример с идентификатором автора
//            dataArrayList.add(listData);
//        }

        filteredDataArrayList.addAll(dataArrayList); // Изначально показываем все элементы

        listAdapter = new ListAdapter(HomeActivity.this, filteredDataArrayList);
        eventsView = findViewById(R.id.eventsListView);
        eventsView.setAdapter(listAdapter);

        noEventsTextView = findViewById(R.id.no_events_text); // Инициализация TextView
        noEventsTextView.setVisibility(View.GONE); // Скрыть по умолчанию

        news = findViewById(R.id.newsBtn);
        saves = findViewById(R.id.savesBtn);
        home = findViewById(R.id.homeBtn);
        chat = findViewById(R.id.chatBtn);
        profile = findViewById(R.id.profileBtn);
        notices = findViewById(R.id.ico_notices);

        notices.setOnClickListener(view -> {
            if (checkAuthentication()) {
            startActivity(new Intent(this, NoticesActivity.class));
        }
        else{
            showRegDialog();
        }});
        news.setOnClickListener(view -> {
            if (checkAuthentication()) {
                startActivity(new Intent(this, NewsActivity.class));
            }
            else{
                showRegDialog();
            }
        });
        saves.setOnClickListener(view -> {
            if (checkAuthentication()) {
                startActivity(new Intent(this, SavedActivity.class));
            }
            else{
                showRegDialog();
            }
        });
//        home.setOnClickListener(v -> {
//            recreate(); // Обновление текущей активности
//        });
        chat.setOnClickListener(view -> {
            if (checkAuthentication()) {
                startActivity(new Intent(this, ChatActivity.class));
            }
            else{
                showRegDialog();
            }
        });
        profile.setOnClickListener(view -> {
            if (checkAuthentication()) {
                startActivity(new Intent(this, ProfileActivity.class));
            }
            else{
                showRegDialog();
            }
        });

    }

    /**
     * Фильтрует список мероприятий по введенному тексту.
     *
     * @param query текст, по которому будет выполняться фильтрация.
     */
    private void filter(String query) {
        filteredDataArrayList.clear();
        if (query.isEmpty()) {
            filteredDataArrayList.addAll(dataArrayList);
        } else {
            String filterPattern = query.toLowerCase().trim();
            for (ListData item : dataArrayList) {
                if (item.name.toLowerCase().contains(filterPattern)) {
                    filteredDataArrayList.add(item);
                }
            }
        }
        listAdapter.notifyDataSetChanged(); // Обновляем адаптер
    }

    /**
     * Фильтрует список мероприятий по названию города.
     *
     * @param cityName название города, по которому будет выполняться фильтрация.
     */
    private void filterByCity(String cityName) {
        filteredDataArrayList.clear();
        for (ListData item : dataArrayList) {
            if (item.address.equals(cityName)) {
                filteredDataArrayList.add(item);
            }
        }
        listAdapter.notifyDataSetChanged(); // Обновляем адаптер
        checkForNoEvents(); // Проверяем наличие мероприятий
    }

    /**
     * Проверяет наличие мероприятий и обновляет видимость текстового поля.
     */
    private void checkForNoEvents() {
        if (filteredDataArrayList.isEmpty()) {
            noEventsTextView.setVisibility(View.VISIBLE); // Показываем сообщение
            eventsView.setVisibility(View.GONE); // Скрываем список мероприятий
        } else {
            noEventsTextView.setVisibility(View.GONE); // Скрываем сообщение
            eventsView.setVisibility(View.VISIBLE); // Показываем список мероприятий
        }
    }

    private boolean checkAuthentication() {
        return isAuthenticated;
    }

    public void showRegDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_reg);

        EditText editTextEmail = dialog.findViewById(R.id.emailEditText);
        EditText editTextPassword = dialog.findViewById(R.id.passEditText);
        EditText editTextRepPassword = dialog.findViewById(R.id.repPassEditText);
        Button buttonReg = dialog.findViewById(R.id.reg_btn);
        Button buttonLogin = dialog.findViewById(R.id.loginBtn);

        buttonReg.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String repPassword = editTextRepPassword.getText().toString();
            // логика проверки входа
            if (checkCredentialsForReg(email, password, repPassword)) {
                isAuthenticated = true; // Установите флаг авторизации
                dialog.dismiss(); // Закрываем диалог
                showLogInDialog();
            } else {
                // Обработка неверных данных
                Toast.makeText(HomeActivity.this, "Ошибка регистрации! Проверьте корректность данных и попробуйте снова", Toast.LENGTH_SHORT).show();

            }
        });

        buttonLogin.setOnClickListener(v -> {
            dialog.dismiss();
            showLogInDialog();
        });

        dialog.show();

    }

    private boolean checkCredentialsForReg(String email, String password, String repPassword) {
        //логика проверки (например, через API или локальную базу данных)
        //return email.equals("user@example.com") && password.equals("password") && password.equals(repPassword);
        return true;
    }

    public void showLogInDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_auth);

        EditText editTextEmail = dialog.findViewById(R.id.emailEditText);
        EditText editTextPassword = dialog.findViewById(R.id.passEditText);
        Button buttonLogin = dialog.findViewById(R.id.log_btn);
        Button buttonReg = dialog.findViewById(R.id.regBtn);

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            // логика проверки входа
            if (checkCredentials(email, password)) {
                isAuthenticated = true; // флаг авторизации
                dialog.dismiss(); // Закрываем диалог
            } else {
                // Обработка неверных данных
                Toast.makeText(HomeActivity.this, "Ошибка входа! Проверьте корректность данных и попробуйте снова", Toast.LENGTH_SHORT).show();

            }
        });

        buttonReg.setOnClickListener(v -> {
            dialog.dismiss();
            showRegDialog();
        });

        dialog.show();
    }

    private boolean checkCredentials(String email, String password) {
        //логика проверки (например, через API или локальную базу данных)
        //return email.equals("user@example.com") && password.equals("password");
        return true;
    }

//    public void goToNotices(View v) {
//        if (checkAuthentication()) {
//            Intent intent = new Intent(this, NoticesActivity.class);
//            startActivity(intent);
//        }
//        else{
//            showRegDialog();
//        }
//    }

//    public void goToNews(View v) {
//        if (checkAuthentication()) {
//            Intent intent = new Intent(this, NewsActivity.class);
//            startActivity(intent);
//        }
//        else{
//            showRegDialog();
//        }
//    }

//    public void goToSaved(View v) {
//        if (checkAuthentication()) {
//            Intent intent = new Intent(this, SavedActivity.class);
//            startActivity(intent);
//        }
//        else{
//            showRegDialog();
//        }
//    }


//    public void goToChat(View v) {
//        if (checkAuthentication()) {
//            Intent intent = new Intent(this, ChatActivity.class);
//            startActivity(intent);
//        }
//        else{
//            showRegDialog();
//        }
//    }

//    public void goToProfile(View v) {
//        if (checkAuthentication()) {
//            Intent intent = new Intent(this, ProfileActivity.class);
//            startActivity(intent);
//        }
//        else{
//            showRegDialog();
//        }
//    }

}