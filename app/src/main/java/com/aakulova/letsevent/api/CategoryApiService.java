package com.aakulova.letsevent.api;

import com.aakulova.letsevent.models.CategoryEvent;
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
public interface CategoryApiService {
    //работа с категориями мероприятий
    @GET("/api/v1/category_events")
    Call<List<CategoryEvent>> getCategories();

    @POST("/api/v1/category_events/save_category_event")
    Call<CategoryEvent> saveCategoryEvent(@Body CategoryEvent categoryEvent);

    @GET("/api/v1/category_events/{name}")
    Call<CategoryEvent> findByNameCategoryEvent(@Path("name") Event nameCategory);

    @PUT("/api/v1/category_events/update_category_event")
    Call<CategoryEvent> updateCategoryEvent(@Path("idCategory") Integer idCategory, @Body CategoryEvent categoryEvent);

    @DELETE("/api/v1/category_events/delete_category_event/{name}")
    Call<Void> deleteCategoryEvent(@Path("name") Event name);
}
