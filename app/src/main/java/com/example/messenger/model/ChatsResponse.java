package com.example.messenger.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatsResponse implements Parcelable {
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

    protected ChatsResponse(Parcel in) {
        chat_id = in.readInt();
        chat_name = in.readString();
        is_group_chat = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(chat_id);
        dest.writeString(chat_name);
        dest.writeByte((byte) (is_group_chat ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChatsResponse> CREATOR = new Creator<ChatsResponse>() {
        @Override
        public ChatsResponse createFromParcel(Parcel in) {
            return new ChatsResponse(in);
        }

        @Override
        public ChatsResponse[] newArray(int size) {
            return new ChatsResponse[size];
        }
    };
}
