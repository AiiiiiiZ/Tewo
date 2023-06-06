package com.actusfibei.chat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private EditText editText;
    private ChatMessageAdapter adapter;
    private ChatDatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editText = findViewById(R.id.editText);
        ListView listView = findViewById(R.id.listView);

        adapter = new ChatMessageAdapter(this);
        listView.setAdapter(adapter);

        dbHelper = new ChatDatabaseHelper(this);

        loadMessages();
    }

    public void onSend() {
        onSend(null);
    }

    public void onSend(View view) {
        String message = editText.getText().toString();
        if (!message.isEmpty()) {
            ChatMessage chatMessage = new ChatMessage(message, System.currentTimeMillis(), "Me");
            dbHelper.addMessage(chatMessage);
            adapter.add(chatMessage);
            editText.setText("");
        }
    }

    private void loadMessages() {
        List<ChatMessage> messages = dbHelper.getMessages();
        adapter.addAll(messages);
    }
}
