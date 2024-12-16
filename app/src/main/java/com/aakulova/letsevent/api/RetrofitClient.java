package com.aakulova.letsevent.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://localhost:8080";
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // Конвертер для JSON
                    .build();
        }
        return retrofit;
    }

    public static CategoryApiService getCategoryApiService() {
        return getInstance().create(CategoryApiService.class);
    }

    public static ChatApiService getChatApiService() {
        return getInstance().create(ChatApiService.class);
    }

    public static CityApiService getCityApiService() {
        return getInstance().create(CityApiService.class);
    }

    public static EventApiService getEventApiService() {
        return getInstance().create(EventApiService.class);
    }

    public static NewsApiService getNewsApiService() {
        return getInstance().create(NewsApiService.class);
    }

    public static NotificationApiService getNotificationApiService() {
        return getInstance().create(NotificationApiService.class);
    }

    public static SubscriptionApiService getSubscriptionApiService() {
        return getInstance().create(SubscriptionApiService.class);
    }

    public static UserApiService getUserApiService() {
        return getInstance().create(UserApiService.class);
    }
}
