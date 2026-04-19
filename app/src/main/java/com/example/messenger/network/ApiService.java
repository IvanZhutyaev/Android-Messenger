package com.example.messenger.network;

import com.example.messenger.model.ChatCreateRequest;
import com.example.messenger.model.ChatsResponse;
import com.example.messenger.model.MessageCreateRequest;
import com.example.messenger.model.MessageResponse;
import com.example.messenger.model.UserLoginRequest;
import com.example.messenger.model.UserRegisterRequest;
import com.example.messenger.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("api/v1/users/register")
    Call<UserResponse> register(@Body UserRegisterRequest body);

    @POST("api/v1/users/login")
    Call<UserResponse> login(@Body UserLoginRequest body);

    @GET("api/v1/users/{id}")
    Call<UserResponse> getUser(@Path("id") int userId);

    @GET("api/v1/users")
    Call<List<UserResponse>> listUsers();

    @POST("api/v1/chats")
    Call<ChatsResponse> createChat(@Body ChatCreateRequest body);

    @GET("api/v1/chats")
    Call<List<ChatsResponse>> getChats();

    @GET("api/v1/chats/{id}")
    Call<ChatsResponse> getChat(@Path("id") int chatId);

    @GET("api/v1/chats/{id}/members")
    Call<List<UserResponse>> getChatMembers(@Path("id") int chatId);

    @POST("api/v1/chats/{id}/members/{user_id}")
    Call<Void> addMember(@Path("id") int chatId,
                         @Path("user_id") int userId
    );

    @DELETE("api/v1/chats/{id}/members/{user_id}")
    Call<Void> removeMember(
            @Path("id") int chatId,
            @Path("user_id") int userId
    );

    @GET("api/v1/chats/{id}/messages")
    Call<List<MessageResponse>> getMessages(@Path("id") int chatId);

    @POST("api/v1/chats/{id}/messages")
    Call<MessageResponse> sendMessage(
            @Path("id") int chatId,
            @Body MessageCreateRequest body);
}
