package com.aakulova.letsevent.models;

public class CityEvent {
    private Integer idCity;
    private Event nameCity;

    public CityEvent(Integer idCity, Event nameCity) {
        this.idCity = idCity;
        this.nameCity = nameCity;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public Event getNameCity() {
        return nameCity;
    }

    public void setNameCity(Event nameCity) {
        this.nameCity = nameCity;
    }
}
