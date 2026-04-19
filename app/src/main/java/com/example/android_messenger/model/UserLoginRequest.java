package com.example.android_messenger.model;

public class UserLoginRequest {
    private final String login;
    private final String password;

    public UserLoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
