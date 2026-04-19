package com.example.messenger.model;

public class UserRegisterRequest {
    private final String login;
    private final String password;
    private  final String first_name;
    private  final String last_name;

    public UserRegisterRequest(String login, String password, String first_name, String last_name) {
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
