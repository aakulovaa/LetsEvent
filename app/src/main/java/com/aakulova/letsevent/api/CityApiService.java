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
public interface CityApiService {
    //работа с городами мероприятий
    @GET("/api/v1/city_events")
    Call<List<CityEvent>> getCity();

    @POST("/api/v1/city_events/save_city_event")
    Call<CityEvent> saveCityEvent(@Body CityEvent cityEvent);

    @GET("/api/v1/city_events/{name}")
    Call<CityEvent> findByNameCityEvent(@Path("name") Event name);

    @PUT("/api/v1/city_events/update_city_event")
    Call<CityEvent> updateCityEvent(@Path("idCity") Integer idCity, @Body CityEvent cityEvent);

    @DELETE("/api/v1/city_events/delete_city_event/{name}")
    Call<Void> deleteCityEvent(@Path("name") Event name);
}
