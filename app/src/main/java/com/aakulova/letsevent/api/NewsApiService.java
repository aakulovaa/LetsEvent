package com.aakulova.letsevent.api;

import com.aakulova.letsevent.models.Event;
import com.aakulova.letsevent.models.News;

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
public interface NewsApiService {
    //работа с новостями
    @GET("/api/v1/news")
    Call<List<News>> getNews();

    @POST("/api/v1/news/save_news")
    Call<News> saveNews(@Body News news);

    @GET("/api/v1/news/{name}")
    Call<News> findEventByTitleNews(@Path("name") String name);

    @PUT("/api/v1/news/update_news")
    Call<News> updateNews(@Path("idNews") Integer idNews, @Body News news);

    @DELETE("/api/v1/news/delete_news/{name}")
    Call<Void> deleteByTitleNews(@Path("name") String name);
}
