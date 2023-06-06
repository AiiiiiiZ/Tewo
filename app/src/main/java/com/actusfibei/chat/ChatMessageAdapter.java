package com.actusfibei.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {
    private final Context context;

    public ChatMessageAdapter(Context context) {
        super(context, 0);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.chat_message, parent, false);
        }

        ChatMessage message = getItem(position);

        TextView senderTextView = convertView.findViewById(R.id.senderTextView);
        TextView messageTextView = convertView.findViewById(R.id.messageTextView);

        senderTextView.setText(message.getSender());
        messageTextView.setText(message.getMessage());

        return convertView;
    }
}
