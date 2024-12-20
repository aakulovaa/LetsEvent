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
import com.aakulova.letsevent.api.CategoryApiService;
import com.aakulova.letsevent.api.CityApiService;
import com.aakulova.letsevent.api.EventApiService;
import com.aakulova.letsevent.api.RetrofitClient;
import com.aakulova.letsevent.api.UserApiService;
import com.aakulova.letsevent.models.CategoryEvent;
import com.aakulova.letsevent.models.CityEvent;
import com.aakulova.letsevent.models.Event;
import com.aakulova.letsevent.user.ChatActivity;
import com.aakulova.letsevent.user.NewsActivity;
import com.aakulova.letsevent.user.NoticesActivity;
import com.aakulova.letsevent.user.ProfileActivity;
import com.aakulova.letsevent.models.User;
import com.aakulova.letsevent.user.UserSession;
import com.aakulova.letsevent.user.UsersListData;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    private ListView eventsView;

    private User currentUser = UserSession.getInstance().getCurrentUser();

    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    private final ArrayList<ListData> filteredDataArrayList = new ArrayList<>();

    private TextView noEventsTextView; // TextView для сообщения об отсутствии мероприятий

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

        CityApiService cityEventApi = RetrofitClient.getInstance().create(CityApiService.class);

        Call<List<CityEvent>> callCity = cityEventApi.getCity();
        /**
         * Метод для получения элементов бд таблицы городов для возможности выбора и фильтрагии по городу
         */
        callCity.enqueue(new Callback<List<CityEvent>>() {
            @Override
            public void onResponse(Call<List<CityEvent>> call, Response<List<CityEvent>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CityEvent> cityEvents = response.body();  // Получаем список городов

                    // Преобразуем список городов в массив String[]
                   String[] cityNames = new String[cityEvents.size()];
                    for (int i = 0; i < cityEvents.size(); i++) {
                        cityNames[i] = cityEvents.get(i).getNameCity();  // Заполняем массив названиями городов
                    }
                    // Устанавливаем адаптер для AutoCompleteTextView
                    AutoCompleteTextView autoCompleteTextView = findViewById(R.id.city_complete_txt);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(HomeActivity.this, R.layout.list_item, cityNames);
                    autoCompleteTextView.setAdapter(arrayAdapter);

                    // Устанавливаем обработчик клика на элемент в списке
                    autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
                        String selectedCity = adapterView.getItemAtPosition(i).toString();
                        filterByCity(selectedCity); // Фильтрация или другие действия с выбранным городом
                    });


                } else {
                    Toast.makeText(HomeActivity.this, "Failed to load cities", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CityEvent>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
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

        CategoryApiService categoryApiService = RetrofitClient.getInstance().create(CategoryApiService.class);

        Call<List<CategoryEvent>> callCategory = categoryApiService.getCategories();
        /**
         * Метод для получения элементов бд таблицы категорий для возможности выбора и фильтрагии по категории
         */
        callCategory.enqueue(new Callback<List<CategoryEvent>>() {
            @Override
            public void onResponse(Call<List<CategoryEvent>> call, Response<List<CategoryEvent>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CategoryEvent> categoryEvents = response.body();

                    String[] categories = new String[categoryEvents.size()];
                    for (int i = 0; i < categoryEvents.size(); i++) {
                        categories[i] = categoryEvents.get(i).getNameCategory();
                    }
                    // Устанавливаем адаптер для AutoCompleteTextView
                    AutoCompleteTextView autoCompleteTextView = findViewById(R.id.category_complete_txt);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(HomeActivity.this, R.layout.list_item, categories);
                    autoCompleteTextView.setAdapter(arrayAdapter);

                    // Устанавливаем обработчик клика на элемент в списке
                    autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
                        String selectedCategory = adapterView.getItemAtPosition(i).toString();
                        filterByCategory(selectedCategory);
                    });


                } else {
                    Toast.makeText(HomeActivity.this, "Failed to load cities", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryEvent>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        EventApiService eventApiService = RetrofitClient.getInstance().create(EventApiService.class);

        Call<List<Event>> callEvent = eventApiService.getEvents();
        ArrayList<ListData> eventsListData = new ArrayList<>();
        /**
         * Метод для получения элементов бд таблицы мероприятий для занесения в список
         */
        callEvent.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Event> events = response.body();
                    for (int i = 0; i < events.size(); i++) {
                        eventsListData.add(new ListData(events.get(i).getNameEvent(), events.get(i).getDescEvent(), events.get(i).getAddressEvent(), events.get(i).getCountOfPeople(), R.drawable.le));
                    }

                } else {
                    Toast.makeText(HomeActivity.this, "Failed to load events", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


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
            startActivity(new Intent(this, NoticesActivity.class));
//            if (currentUser.isAuthenticated()) {
//                startActivity(new Intent(this, NoticesActivity.class));
//            } else {
//                //showRegDialog();
//                showLogInDialog();
//            }
        });
        news.setOnClickListener(view -> {
            startActivity(new Intent(this, NewsActivity.class));
//            if (currentUser.isAuthenticated()) {
//                startActivity(new Intent(this, NewsActivity.class));
//            } else {
//                //showRegDialog();
//                showLogInDialog();
//            }
        });
        saves.setOnClickListener(view -> {
            startActivity(new Intent(this, SavedActivity.class));
//            if (currentUser.isAuthenticated()) {
//                startActivity(new Intent(this, SavedActivity.class));
//            } else {
//                //showRegDialog();
//                showLogInDialog();
//            }
        });
        home.setOnClickListener(v -> {
            recreate(); // Обновление текущей активности
        });
        chat.setOnClickListener(view -> {
            startActivity(new Intent(this, ChatActivity.class));
//            if (currentUser.isAuthenticated()) {
//                startActivity(new Intent(this, ChatActivity.class));
//            } else {
//                //showRegDialog();
//                showLogInDialog();
//            }
        });
        profile.setOnClickListener(view -> {
            startActivity(new Intent(this, ProfileActivity.class));
//            if (currentUser.isAuthenticated()) {
//                startActivity(new Intent(this, ProfileActivity.class));
//            } else {
//                //showRegDialog();
//                showLogInDialog();
//            }
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
     * Фильтрует список мероприятий по категории.
     *
     * @param categoryName - название категории, по которому будет выполняться фильтрация.
     */
    private void filterByCategory(String categoryName) {
        filteredDataArrayList.clear();
        for (ListData item : dataArrayList) {
            if (item.address.equals(categoryName)) {
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
//            if (checkCredentialsForReg(email, password, repPassword)) {
//                dialog.dismiss(); // Закрываем диалог
//
//                Integer id = 1;
//                String username = User.generateRandomUsername();
//                String profileImageUrl = ""; // URL изображения профиля
//                String accountType = "regular"; // Или "business"
//
//                currentUser = new User(id, username, email, password, repPassword, profileImageUrl, accountType);
//                UserSession.getInstance().setCurrentUser(currentUser);
//
//                showLogInDialog();
//            } else {
//                // Обработка неверных данных
//                Toast.makeText(HomeActivity.this, "Ошибка регистрации! Проверьте корректность данных и попробуйте снова", Toast.LENGTH_SHORT).show();
//            }
        });

        buttonLogin.setOnClickListener(v -> {
            dialog.dismiss();
            showLogInDialog();
        });

        dialog.show();

    }


    public void showLogInDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_auth);

        EditText editTextEmail = dialog.findViewById(R.id.emailEditText);
        EditText editTextPassword = dialog.findViewById(R.id.passEditText);
        Button buttonLogin = dialog.findViewById(R.id.log_btn);
        Button buttonReg = dialog.findViewById(R.id.regBtn);

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();  // Убираем лишние пробелы
            String password = editTextPassword.getText().toString().trim();

            // Проверка на пустые поля
            if (email.isEmpty() || password.isEmpty()) {
                // Если одно из полей пустое, показываем сообщение и выходим из метода
                Toast.makeText(HomeActivity.this, "Пожалуйста, введите и email, и пароль", Toast.LENGTH_SHORT).show();
                return; // Выход из метода, не выполняем запрос
            }

            UserApiService userApiService = RetrofitClient.getInstance().create(UserApiService.class);

            // Вызов API с переданным email
            Call<User> call = userApiService.findByEmail(email);

            // Асинхронный запрос с обработчиками для ответа и ошибки
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Если пользователь найден, получаем данные
                        User user = response.body();
                        String userEmail = user.getEmailUser();
                        String userPass = user.getPasswordUser();

                        // Проверка пароля
                        if (!userPass.equals(password)) {
                            Toast.makeText(HomeActivity.this, "Неверный пароль. Попробуйте снова.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Устанавливаем пользователя в UserSession
                        currentUser.setAuthenticated(true);
                        UserSession.getInstance().setCurrentUser(user);

                        dialog.dismiss();

                    } else {
                        // Ошибка входа, если пользователь не найден
                        Toast.makeText(HomeActivity.this, "Ошибка входа! Проверьте корректность данных и попробуйте снова", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Ошибка запроса (например, если нет интернета или сервер не доступен)
                    Toast.makeText(HomeActivity.this, "Ошибка: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        buttonReg.setOnClickListener(v -> {
            dialog.dismiss();
            showRegDialog();
        });

        dialog.show();
    }


}