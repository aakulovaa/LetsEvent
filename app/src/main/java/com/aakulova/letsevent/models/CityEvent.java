package com.aakulova.letsevent.models;

import java.util.HashSet;
import java.util.Set;

public class CityEvent {
    private Integer idCity;
    private String nameCity;
    private Set<Event> events = new HashSet<>();

    public CityEvent(Integer idCity, String nameCity, Set<Event> events) {
        this.idCity = idCity;
        this.nameCity = nameCity;
        this.events = events;
    }

    public CityEvent(Integer idCity, String nameCity) {
        this.idCity = idCity;
        this.nameCity = nameCity;
    }

    public CityEvent(String nameCity) {
        this.nameCity = nameCity;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
