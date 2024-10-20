package com.aakulova.letsevent.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.SavedActivity;
import com.aakulova.letsevent.event.HomeActivity;

import java.util.Objects;


public class SettingsActivity extends AppCompatActivity {

    private boolean nightMode, businessMode;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private User currentUser;
    private EditText usernameEditText, emailEditText;
    private ImageView profileImageView;
    private Uri profileImageUri;
    private LinearLayout editProfileLayout;
    private LinearLayout changePasswordLayout;

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        currentUser = new User("1", "Ann","user@example.com","", "regular");

        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        profileImageView = findViewById(R.id.profileImageView);
        Button switchToBusinessButton = findViewById(R.id.switchToBusinessButton);
        Button saveChangesButton = findViewById(R.id.saveChangesButton);
        Button changePasswordButton = findViewById(R.id.changePasswordButton);
        Button editProfileButton = findViewById(R.id.editProfileButton);
        editProfileLayout = findViewById(R.id.editProfileLayout);
        changePasswordLayout = findViewById(R.id.changePasswordLayout);
        TextView accountTypeTextView = findViewById(R.id.accountTypeTextView);

        usernameEditText.setText(currentUser.getUsername());
        emailEditText.setText(currentUser.getEmail());

        if(Objects.equals(currentUser.getAccountType(), "regular")){
            accountTypeTextView.setText("Бизнес аккаунт");
        }
        else {
            accountTypeTextView.setText("Личный аккаунт");
        }

        switchToBusinessButton.setOnClickListener(v -> {
            currentUser.setAccountType("business"); // Меняем тип аккаунта
            Intent intent = new Intent(SettingsActivity.this, BusinessProfileActivity.class);
            startActivity(intent);
        });


        // Запуск выбора изображения
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                profileImageUri = result.getData().getData();
                profileImageView.setImageURI(profileImageUri);
            }
        });

        profileImageView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });

        editProfileButton.setOnClickListener(v -> {
            editProfileLayout.setVisibility(View.VISIBLE);
            changePasswordLayout.setVisibility(View.GONE);
        });

        changePasswordButton.setOnClickListener(v -> {
            editProfileLayout.setVisibility(View.GONE);
            changePasswordLayout.setVisibility(View.VISIBLE);
        });


        saveChangesButton.setOnClickListener(v -> updateProfile());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        SwitchCompat switchCompatTheme = findViewById(R.id.switch_theme);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("nightMode", false);

        if(nightMode){
            switchCompatTheme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchCompatTheme.setOnClickListener(view -> {
            if (nightMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor = sharedPreferences.edit();
                editor.putBoolean("nightMode", false);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor = sharedPreferences.edit();
                editor.putBoolean("nightMode", true);
            }
            editor.apply();
        });//работает в светлой теме телефона

//        SwitchCompat switchCompatAkk = findViewById(R.id.switch_profile);
//        businessMode = sharedPreferences.getBoolean("businessMode", false);
//
//        if(businessMode){
//            switchCompatAkk.setChecked(true);
//        }

    }

    private void updateProfile() {
        String newUsername = usernameEditText.getText().toString();
        String newEmail = emailEditText.getText().toString();

        // Проверка на пустые поля
        if (newUsername.isEmpty() || newEmail.isEmpty()) {
            Toast.makeText(this, "Username and email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Обновление данных пользователя
        currentUser.setUsername(newUsername);
        currentUser.setEmail(newEmail);
        if (profileImageUri != null) {
            currentUser.setProfileImageUrl(profileImageUri.toString());
        }

        // Здесь вы можете сохранить изменения в базе данных

        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
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
    public void goToProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}