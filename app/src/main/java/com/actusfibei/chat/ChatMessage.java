package com.actusfibei.chat;

public class ChatMessage {
    private final String message;
    private final long timestamp;
    private final String sender;

    public ChatMessage(String message, long timestamp, String sender) {
        this.message = message;
        this.timestamp = timestamp;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSender() {
        return sender;
    }
}

