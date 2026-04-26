package com.example.messenger.model;

public class MessageCreateRequest {
    private final int sender_id;
    private final int chat_id;
    private final String message_text;

    public MessageCreateRequest(int sender_id, int chat_id, String message_text) {
        this.sender_id = sender_id;
        this.chat_id = chat_id;
        this.message_text = message_text;
    }
}
