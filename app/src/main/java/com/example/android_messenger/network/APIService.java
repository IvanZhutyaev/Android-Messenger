package com.example.android_messenger.network;

import com.example.android_messenger.model.ChatCreateRequest;
import com.example.android_messenger.model.ChatsResponce;
import com.example.android_messenger.model.MessageCreateRequest;
import com.example.android_messenger.model.MessageResponce;
import com.example.android_messenger.model.UserLoginRequest;
import com.example.android_messenger.model.UserRegisterRequest;
import com.example.android_messenger.model.UserResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @POST("api/v1/users/register")
    Call<UserResponce> register(@Body UserRegisterRequest body);
    @POST("api/v1/users/login")
    Call<UserResponce> login(@Body UserLoginRequest body);

    @GET("api/v1/users/{id}")
    Call<UserResponce> getUser(@Path("id") int userId);

    @GET("api/v1/users")
    Call<List<UserResponce>> listUsers();

    @POST("api/v1/chats")
    Call<ChatsResponce> createChat(@Body ChatCreateRequest body);
    @GET("api/v1/chats")
    Call<List<ChatsResponce>> getChats();

    @GET("api/v1/chats/{id}")
    Call<ChatsResponce> getChat(@Path("id") int chatId);

    @GET("api/v1/chats/{id}/members")
    Call<List<UserResponce>> getChatMembers(@Path("id") int chatId);

    @POST("api/v1/chats/{id}/members/{user_id}")
    Call<Void> addMember(@Path("id") int chatId,
                        @Path("user_id") int userId
    );

    @DELETE("api/v1/chats/{id}/members/{user_id}")
    Call<Void> removeMember(@Path("id") int chatId,
                            @Path("user_id") int userId
    );

    @GET("api/v1/chats/{id}/messages")
    Call<List<MessageResponce>> getMessages(@Path("id") int chatId);

    @POST("api/v1/chats/{id}/messages")

    Call<MessageResponce> sendMessage(@Path("id") int chatId, @Body MessageCreateRequest body);
}
