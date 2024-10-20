package com.aakulova.letsevent.event;

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

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.SavedActivity;
import com.aakulova.letsevent.user.ChatActivity;
import com.aakulova.letsevent.user.NewsActivity;
import com.aakulova.letsevent.user.NoticesActivity;
import com.aakulova.letsevent.user.ProfileActivity;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    private final String[] city = {"Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Нижний Новгород",
            "Казань", "Челябинск", "Омск", "Самара", "Ростов-на-Дону",
            "Уфа", "Красноярск", "Воронеж", "Пермь", "Саратов",
            "Тюмень", "Ижевск", "Барнаул", "Ульяновск", "Калуга",
            "Сочи", "Тула", "Томск", "Ярославль", "Астрахань",
            "Рязань", "Владивосток", "Магнитогорск", "Ставрополь", "Липецк",
            "Брянск", "Иваново", "Мурманск", "Чебоксары", "Кемерово",
            "Тверь", "Архангельск", "Сургут", "Набережные Челны", "Хабаровск",
            "Симферополь", "Нижнекамск", "Новороссийск", "Кострома", "Петрозаводск",
            "Сланцы", "Чита", "Ноябрьск", "Находка", "Благовещенск",
            "Комсомольск-на-Амуре", "Рубцовск", "Рыбинск", "Кызыл", "Курган",
            "Саранск", "Магадан", "Якутск", "Элиста", "Грозный",
            "Махачкала", "Назрань", "Нальчик", "Черкесск", "Петропавловск-Камчатский",
            "Улан-Удэ", "Сыктывкар", "Ханты-Мансийск", "Нижний Тагил", "Златоуст",
            "Ангарск", "Арзамас", "Нижневартовск", "Каспийск", "Калининград",
            "Саратов", "Таганрог", "Шадринск", "Усть-Илимск", "Новокузнецк",
            "Смоленск", "Киров", "Балаково", "Миасс", "Серпухов",
            "Подольск", "Люберцы", "Химки", "Черкесск", "Тверь",
            "Раменское", "Астрахань", "Славгород", "Жуковский", "Клин",
            "Дзержинск", "Кострома", "Северодвинск", "Электросталь", "Камышин",
            "Тихвин", "Волжский", "Сочи", "Находка", "Мурманск",
            "Кисловодск", "Сызрань", "Туапсе", "Калуга", "Томск",
            "Глазов", "Чебоксары", "Елец", "Узловая", "Шахты",
            "Старый Оскол", "Кумертау", "Агидель", "Тольятти", "Кунгур",
            "Тверь", "Ноябрьск", "Нальчик", "Энгельс", "Ковров",
            "Смоленск", "Кострома", "Гатчина", "Нижневартовск", "Благовещенск",
            "Суровикино", "Пятигорск", "Таганрог", "Салават", "Сосновый Бор",
            "Калуга", "Зеленоград", "Королёв", "Наро-Фоминск", "Лобня",
            "Истра", "Серпухов", "Люберцы", "Мытищи", "Долгопрудный",
            "Щёлково", "Электросталь", "Обнинск", "Дзержинск", "Пушкин",
            "Кострома", "Зеленодольск", "Котлас", "Сыктывкар", "Калининград",
            "Усть-Лабинск", "Владикавказ", "Славгород", "Усть-Кут", "Невинномысск",
            "Майкоп", "Находка", "Стародуб", "Петушки", "Пробуждение"};

    private final int[] eventImage = {R.drawable.le,R.drawable.le,R.drawable.le};
    private final int[] countPeople = {10, 253, 382};
    private final String[] eventName = {"BLACK STAR PARTY", "выставка", "арт-встреча"};
    private final String[] eventDate = {"28 сентября 11:00", "28 сентября 11:00", "28 сентября 11:00"};
    private final String[] eventDesc = {"BLACK STAR PARTY", "выставка", "арт-встреча"};
    private final String[] eventAddr = {"Москва", "Воронеж", "Орел"};

    private ListView eventsView;

    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();

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

        /** список мероприятий*/
        for (int i = 0; i < eventName.length; i++) {
            ListData listData = new ListData(eventName[i], eventDate[i], eventDesc[i], eventAddr[i], countPeople[i], eventImage[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new ListAdapter(HomeActivity.this, dataArrayList);
        eventsView = findViewById(R.id.eventsListView);
        eventsView.setAdapter(listAdapter);
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