package com.aakulova.letsevent.models;

import java.time.LocalDateTime;

public class News {
    private Integer idNews;
    private User userNews;
    private String titleNews;
    private String descNews;
    private int imgSrcNews;
    private LocalDateTime createdAt;

    public News(Integer idNews, User userNews, String titleNews, String descNews, int imgSrcNews, LocalDateTime createdAt) {
        this.idNews = idNews;
        this.userNews = userNews;
        this.titleNews = titleNews;
        this.descNews = descNews;
        this.imgSrcNews = imgSrcNews;
        this.createdAt = createdAt;
    }

    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
    }

    public User getUserNews() {
        return userNews;
    }

    public void setUserNews(User userNews) {
        this.userNews = userNews;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getDescNews() {
        return descNews;
    }

    public void setDescNews(String descNews) {
        this.descNews = descNews;
    }

    public int getImgSrcNews() {
        return imgSrcNews;
    }

    public void setImgSrcNews(int imgSrcNews) {
        this.imgSrcNews = imgSrcNews;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
