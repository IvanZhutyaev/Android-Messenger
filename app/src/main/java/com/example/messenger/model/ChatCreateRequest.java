package com.example.messenger.model;

public class ChatCreateRequest {
    private final String chat_name;
    private final boolean is_group_chat;

    public ChatCreateRequest(String chat_name, boolean is_group_chat) {
        this.chat_name = chat_name;
        this.is_group_chat = is_group_chat;
    }
}
