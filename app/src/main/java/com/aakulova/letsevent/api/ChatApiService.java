package com.aakulova.letsevent.api;

import com.aakulova.letsevent.models.CategoryEvent;
import com.aakulova.letsevent.models.Chat;
import com.aakulova.letsevent.models.Event;
import com.aakulova.letsevent.models.User;

import java.time.LocalDateTime;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Интерфейс, описывающий запросы для работы с REST API
 */
public interface ChatApiService {
    //работа с чатами
    @GET("/api/v1/chats")
    Call<List<Chat>> getChats();

    @POST("/api/v1/chats/save_chat")
    Call<Chat> saveChat(@Body Chat chat);

    @PUT("/api/v1/chats/update_chat")
    Call<Chat> updateChat(@Path("idChat") Integer idChat, @Body Chat chat);

    @DELETE("/api/v1/chats/delete_by_sender_and_receiver/{userSender}/{userReceiver}")
    Call<Void> deleteByUserSenderAndUserReceiver(@Path("userSender") User userSender, @Path("userReceiver") User userReceiver);

    @DELETE("/api/v1/chats/delete_by_sender/{userSender}")
    Call<Void> deleteByUserSender(@Path("userSender") User userSender);

    @DELETE("/api/v1/chats/delete_by_receiver/{userReceiver}")
    Call<Void> deleteByUserReceiver(@Path("userReceiver") User userReceiver);

    @DELETE("/api/v1/chats/delete_by_time/{startTime}/{endTime}")
    Call<Void> deleteBySendAtBetween(@Path("startTime") LocalDateTime startTime, @Path("endTime") LocalDateTime endTime);

    @GET("/api/v1/chats/{userSender}/{startTime}/{endTime}")
    Call<Chat> findByUserSenderAndSendAtBetween(@Path("userSender") User userSender, @Path("startTime") LocalDateTime startTime, @Path("endTime") LocalDateTime endTime);

    @GET("/api/v1/chats/{userSender}/{userReceiver}")
    Call<Chat> findByUserSenderAndUserReceiver(@Path("userSender") User userSender, @Path("userReceiver") User userReceiver);

    @GET("/api/v1/chats/{userSender}")
    Call<Chat> findByUserSender(@Path("userSender") User userSender);

    @GET("/api/v1/chats/{userReceiver}")
    Call<Chat> findByUserReceiver(@Path("userReceiver") User userReceiver);

    @GET("/api/v1/chats/{startTime}/{endTime}")
    Call<Chat> findBySendAtBetween(@Path("startTime") LocalDateTime startTime, @Path("endTime") LocalDateTime endTime);

}
