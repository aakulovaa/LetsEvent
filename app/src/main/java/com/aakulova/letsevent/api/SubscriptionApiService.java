package com.aakulova.letsevent.api;

import com.aakulova.letsevent.models.Notification;
import com.aakulova.letsevent.models.Subscription;
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
public interface SubscriptionApiService {
    //работа с подписками
    @GET("/api/v1/subscription")
    Call<List<Subscription>> getSubscriptions();

    @POST("/api/v1/subscription/save_subscription")
    Call<Subscription> saveSubscriptions(@Body Subscription subscription);

    @GET("/api/v1/subscription/{subscriber}")
    Call<Subscription> findBySubscriber(@Path("subscriber") User subscriber);

    @GET("/api/v1/subscription/{subscribedTo}")
    Call<Subscription> findBySubscribedTo(@Path("subscriber") User subscribedTo);

    @PUT("/api/v1/subscription/update_subscription")
    Call<Subscription> updateSubscriptions(@Path("idSubscription") Integer idSubscription, @Body Subscription subscription);

    @DELETE("/api/v1/subscription/delete_subscriber/{subscriber}")
    Call<Void> deleteBySubscriber(@Path("subscriber") User subscriber);

    @DELETE("/api/v1/subscription/delete_subscribed_to/{subscribedTo}")
    Call<Void> deleteBySubscribedTo(@Path("subscribedTo") User subscribedTo);

}
