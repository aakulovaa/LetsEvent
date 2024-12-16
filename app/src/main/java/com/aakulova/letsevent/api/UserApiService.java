package com.aakulova.letsevent.api;

import com.aakulova.letsevent.models.Notification;
import com.aakulova.letsevent.models.User;

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
public interface UserApiService {
    //работа с пользователями
    @GET("/api/v1/users")
    Call<List<User>> getUsers();

    @POST("/api/v1/users/save_user")
    Call<User> saveUser(@Body User user);

    @GET("/api/v1/users/{email}")
    Call<User> findByEmail(@Path("name") String email);

    @PUT("/api/v1/users/update_user")
    Call<User> updateUser(@Path("idUser") Integer idUser, @Body User user);

    @DELETE("/api/v1/users/delete_user/{email}")
    Call<Void> deleteUser(@Path("email") String email);
}
