package com.aakulova.letsevent.api;

import com.aakulova.letsevent.models.CategoryEvent;
import com.aakulova.letsevent.models.CityEvent;
import com.aakulova.letsevent.models.Event;

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
public interface EventApiService {
    //работа с мероприятиями
    @GET("/api/v1/events")
    Call<List<Event>> getEvents();

    @POST("/api/v1/events/save_event")
    Call<Event> saveEvent(@Body Event event);

    @GET("/api/v1/events/{name}")
    Call<Event> findByName(@Path("name") String name);

    @PUT("/api/v1/events/update_event")
    Call<Event> updateEvent(@Path("idCity") Integer idEvent, @Body Event event);

    @DELETE("/api/v1/events/delete_event/{name}")
    Call<Void> deleteEvent(@Path("name") String name);

    @GET("/api/v1/events/{userId}/attended")
    Call<List<Event>> getEventsAttendedByUser();

    @GET("/api/v1/events/{userId}/saved")
    Call<List<Event>> getEventsSavedByUser();
}
