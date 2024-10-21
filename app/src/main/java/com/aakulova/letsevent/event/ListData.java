package com.aakulova.letsevent.event;

import java.util.Objects;

public class ListData {
    String name, date, desc, address;
    int countPeople, image;

    public ListData(String name, String date, String desc, String address, int countPeople, int image) {
        this.name = name;
        this.date = date;
        this.desc = desc;
        this.address = address;
        this.countPeople = countPeople;
        this.image = image;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, date, address);
    }

}


