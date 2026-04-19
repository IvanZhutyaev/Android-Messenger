package com.example.android_messenger.data;

import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF="messenger_session";
    private static final String KEY_USER_ID="user_id";
    private static final String KEY_LOGIN="login";
    private final SharedPreferences prefs;


    public SessionManager(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public void sevUser(int userId, String login){
        prefs.edit()
                .putInt(KEY_USER_ID, userId)
                .putString(KEY_LOGIN, login)
                .apply();
    }

    public int getUserId(){
        return prefs.getInt(KEY_USER_ID, -1);
    }
    public String login(){
        return prefs.getString(KEY_LOGIN, null);
    }
    public boolean isLoggedIn(){
        return getUserId()!=-1;
    }

    public void clear(){
        prefs.edit().clear().apply();
    }
}
