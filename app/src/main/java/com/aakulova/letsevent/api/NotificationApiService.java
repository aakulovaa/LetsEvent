package com.aakulova.letsevent.api;

import com.aakulova.letsevent.models.News;
import com.aakulova.letsevent.models.Notification;

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
public interface NotificationApiService {
    //работа с уведомлениями
    @GET("/api/v1/notification")
    Call<List<Notification>> getNotifications();

    @POST("/api/v1/notification/save_notification")
    Call<Notification> saveNotifications(@Body Notification notification);

    @GET("/api/v1/notification/{name}")
    Call<Notification> findByNameNotifications(@Path("name") String name);

    @PUT("/api/v1/notification/update_notification")
    Call<Notification> updateNotifications(@Path("idNotification") Integer idNotification, @Body Notification notification);

    @DELETE("/api/v1/notification/delete_notification/{name}")
    Call<Void> deleteNotifications(@Path("name") String name);
}
