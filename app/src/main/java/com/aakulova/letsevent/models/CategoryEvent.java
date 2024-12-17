package com.aakulova.letsevent.models;

import java.util.HashSet;
import java.util.Set;

public class CategoryEvent {
    private Integer idCategory;
    private String nameCategory;
    private Set<Event> events = new HashSet<>();

    public CategoryEvent(Integer idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public CategoryEvent(Integer idCategory, String nameCategory, Set<Event> events) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.events = events;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
