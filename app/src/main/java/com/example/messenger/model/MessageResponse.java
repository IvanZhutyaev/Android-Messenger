package com.example.messenger.model;

public class MessageResponse {
    private int message_id;
    private String message_text;
    private int chat_id;
    private int sender_id;
    private String sent_at;

    public int getMessage_id() {
        return message_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public int getChat_id() {
        return chat_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public String getSent_at() {
        return sent_at;
    }
}
