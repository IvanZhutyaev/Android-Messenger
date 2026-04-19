package com.example.android_messenger.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserResponce implements Parcelable{
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
    protected UserResponce(Parcel in) {
        user_id = in.readInt();
        login = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        username = in.readString();
        bio = in.readString();
        avatar_url = in.readString();
        phone_number = in.readString();
    }

    public static final Creator<UserResponce> CREATOR = new Creator<UserResponce>() {
        @Override
        public UserResponce createFromParcel(Parcel in) {
            return new UserResponce(in);
        }

        @Override
        public UserResponce[] newArray(int size) {
            return new UserResponce[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(user_id);
        parcel.writeString(login);
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        parcel.writeString(username);
        parcel.writeString(bio);
        parcel.writeString(avatar_url);
        parcel.writeString(phone_number);
    }
}
