package com.example.android_messenger.model;

public class UserRegisterRequest {
    private final String login;
    private final String password;
    private final String first_name;
    private  final String last_name;

    public UserRegisterRequest(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        first_name = firstName;
        last_name = lastName;
    }
}
