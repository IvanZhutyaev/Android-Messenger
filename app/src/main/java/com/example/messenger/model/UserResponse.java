package com.example.messenger.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserResponse implements Parcelable {

    private int user_id;
    private String login;
    private String first_name;
    private String last_name;
    private String username;
    private String bio;
    private String avatar_url;
    private String phone_number;

    public int getUser_id() {
        return user_id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getPhone_number() {
        return phone_number;
    }

    protected UserResponse(Parcel in) {
        user_id = in.readInt();
        login = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        username = in.readString();
        bio = in.readString();
        avatar_url = in.readString();
        phone_number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user_id);
        dest.writeString(login);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(username);
        dest.writeString(bio);
        dest.writeString(avatar_url);
        dest.writeString(phone_number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserResponse> CREATOR = new Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel in) {
            return new UserResponse(in);
        }

        @Override
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };
}
