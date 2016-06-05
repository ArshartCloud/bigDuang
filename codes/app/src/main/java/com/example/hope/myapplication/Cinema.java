package com.example.hope.myapplication;

/**
 * Created by hope on 5/29/16.
 */
public class Cinema {
    private int cinemaId;
    private String cinemaName;
    private String city;
    private String address;


    public String getcinemaName() {
        return this.cinemaName;
    }
    public void setcinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public int getcinemaId() {
        return this.cinemaId;
    }
    public void setcinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getcity() {
        return this.city;
    }
    public void setcity(String city) {
        this.city = city;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getaddress() {
        return this.address;
    }

}
