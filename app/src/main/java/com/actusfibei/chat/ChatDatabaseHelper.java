package com.actusfibei.chat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "chat.db";
    private static final int DATABASE_VERSION = 1;

    public ChatDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE messages (id INTEGER PRIMARY KEY AUTOINCREMENT, message TEXT, timestamp INTEGER, sender TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade as needed
    }

    public void addMessage(ChatMessage message) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("message", message.getMessage());
        values.put("timestamp", message.getTimestamp());
        values.put("sender", message.getSender());

        db.insert("messages", null, values);
    }

    public List<ChatMessage> getMessages() {
        List<ChatMessage> messages = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("messages", null, null, null, null, null, "timestamp ASC");

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String message = cursor.getString(cursor.getColumnIndex("message"));
            @SuppressLint("Range") long timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
            @SuppressLint("Range") String sender = cursor.getString(cursor.getColumnIndex("sender"));

            messages.add(new ChatMessage(message, timestamp, sender));
        }

        cursor.close();

        return messages;
    }
}