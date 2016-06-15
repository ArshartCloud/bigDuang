package com.example.models;

import java.util.Date;

/**
 * Created by Arshart on 2016/6/15.
 */
public class Showtime {
    private int id;
    private double price;
//    private String data;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
