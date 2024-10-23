package com.aakulova.letsevent.event;

import java.util.Objects;

public class ListData {
    String name, date, desc, address;
    int countPeople, image;
    //String authorId; // Идентификатор автора мероприятия

    public ListData(String name, String date, String desc, String address, int countPeople, int image) {
        this.name = name;
        this.date = date;
        this.desc = desc;
        this.address = address;
        this.countPeople = countPeople;
        this.image = image;
    }

//    public ListData(String name, String date, String desc, String address, int countPeople, int image, String authorId) {
//        this.name = name;
//        this.date = date;
//        this.desc = desc;
//        this.address = address;
//        this.countPeople = countPeople;
//        this.image = image;
//        this.authorId = authorId; // Инициализация идентификатора автора
//    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ListData listData = (ListData) obj;
        return name.equals(listData.name) &&
                date.equals(listData.date) &&
                address.equals(listData.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, address);
    }

}


