package com.aakulova.letsevent.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Event {
    private Integer idEvent;
    private String nameEvent;
    private LocalDate dateEvent;
    private Set<CityEvent> cityEvent = new HashSet<>();
    private String addressEvent;
    private String descEvent;
    private int countOfPeople;
    private Set<CategoryEvent> categoryEvent = new HashSet<>();
    private String imgSrcUser;
    private User author;
    private Set<User> usersAttended = new HashSet<>();
    private Set<User> usersSaved = new HashSet<>();

    public Event(Integer idEvent, String nameEvent, LocalDate dateEvent, Set<CityEvent> cityEvent, String addressEvent, String descEvent, int countOfPeople, Set<CategoryEvent> categoryEvent, String imgSrcUser, User author, Set<User> usersAttended, Set<User> usersSaved) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
        this.dateEvent = dateEvent;
        this.cityEvent = cityEvent;
        this.addressEvent = addressEvent;
        this.descEvent = descEvent;
        this.countOfPeople = countOfPeople;
        this.categoryEvent = categoryEvent;
        this.imgSrcUser = imgSrcUser;
        this.author = author;
        this.usersAttended = usersAttended;
        this.usersSaved = usersSaved;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Set<CityEvent> getCityEvent() {
        return cityEvent;
    }

    public void setCityEvent(Set<CityEvent> cityEvent) {
        this.cityEvent = cityEvent;
    }

    public String getAddressEvent() {
        return addressEvent;
    }

    public void setAddressEvent(String addressEvent) {
        this.addressEvent = addressEvent;
    }

    public String getDescEvent() {
        return descEvent;
    }

    public void setDescEvent(String descEvent) {
        this.descEvent = descEvent;
    }

    public int getCountOfPeople() {
        return countOfPeople;
    }

    public void setCountOfPeople(int countOfPeople) {
        this.countOfPeople = countOfPeople;
    }

    public Set<CategoryEvent> getCategoryEvent() {
        return categoryEvent;
    }

    public void setCategoryEvent(Set<CategoryEvent> categoryEvent) {
        this.categoryEvent = categoryEvent;
    }

    public String getImgSrcUser() {
        return imgSrcUser;
    }

    public void setImgSrcUser(String imgSrcUser) {
        this.imgSrcUser = imgSrcUser;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<User> getUsersAttended() {
        return usersAttended;
    }

    public void setUsersAttended(Set<User> usersAttended) {
        this.usersAttended = usersAttended;
    }

    public Set<User> getUsersSaved() {
        return usersSaved;
    }

    public void setUsersSaved(Set<User> usersSaved) {
        this.usersSaved = usersSaved;
    }
}
