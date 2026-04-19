package com.example.android_messenger.model;

public class ChatCreateRequest {
    private final String chat_name;
    private final boolean is_group_chat;

    public ChatCreateRequest(String chatName, boolean isGroupChat) {
        chat_name = chatName;
        is_group_chat = isGroupChat;
    }
}
