package com.example.models;

/**
 * Created by Arshart on 2016/6/4 0004.
 */
public class Customer {
    private int id;
    private String name;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
