package com.example.android_messenger.model;

public class MessageCreateRequest {
    private final int sender_id;
    private final int chat_id;
    private final String message_text;


    public MessageCreateRequest(int senderId, int chatId, String messageText) {
        sender_id = senderId;
        chat_id = chatId;
        message_text = messageText;
    }
}
