package com.aakulova.letsevent.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakulova.letsevent.R;
import com.aakulova.letsevent.event.HomeActivity;
import com.aakulova.letsevent.models.User;
import com.aakulova.letsevent.news.NewsAdapter;
import com.aakulova.letsevent.news.NewsListData;
import com.aakulova.letsevent.user.UserSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DialogActivity extends AppCompatActivity {
    private EditText editText;
    private ImageButton imageBtn,sendBtn;
    private TextView userReceiving;
    ListView messageView;
    ArrayList<MessageListData> messageList;
    MessageAdapter adapter;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageBtn = findViewById(R.id.ico_image);
        sendBtn = findViewById(R.id.ico_send_btn);
        editText = findViewById(R.id.messageText);

        sendBtn.setOnClickListener(view -> sendMessage());
        imageBtn.setOnClickListener(view -> openGallery());

        messageView = findViewById(R.id.list_of_messages);
        messageList = new ArrayList<>();

        userReceiving = findViewById(R.id.userReceiving);
        userReceiving.setText("Admin2");

    }

    private void sendMessage() {
        String inputText = editText.getText().toString().trim();
        if (!inputText.isEmpty()) {
            String userName = "Admin";
            String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(new Date());
            String inputMessage = editText.getText().toString();


            messageList.add(new MessageListData(userName, currentDateTime, inputMessage));
            adapter = new MessageAdapter(this, messageList);
            messageView.setAdapter(adapter);
            editText.setText("");
        }
    }

    private void openGallery() {

    }

    public void goToBack(View v) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}