package com.aakulova.letsevent.models;

public class CategoryEvent {
    private Integer idCategory;
    private Event nameCategory;

    public CategoryEvent(Integer idCategory, Event nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Event getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(Event nameCategory) {
        this.nameCategory = nameCategory;
    }
}
