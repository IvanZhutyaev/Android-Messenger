package com.example.android_messenger.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ChatsResponce implements Parcelable {
    private int chat_id;
    private String chat_name;
    private boolean is_group_chat;
    public int getChat_id() {
        return chat_id;
    }

    public String getChat_name() {
        return chat_name;
    }

    public boolean isIs_group_chat() {
        return is_group_chat;
    }
    protected ChatsResponce(Parcel in) {
        chat_id = in.readInt();
        chat_name = in.readString();
        is_group_chat = in.readByte() != 0;
    }

    public static final Creator<ChatsResponce> CREATOR = new Creator<ChatsResponce>() {
        @Override
        public ChatsResponce createFromParcel(Parcel in) {
            return new ChatsResponce(in);
        }

        @Override
        public ChatsResponce[] newArray(int size) {
            return new ChatsResponce[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(chat_id);
        parcel.writeString(chat_name);
        parcel.writeByte((byte) (is_group_chat ? 1 : 0));
    }
}
