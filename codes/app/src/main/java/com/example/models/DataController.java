package com.example.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arshart on 2016/6/5 0005.
 */
public class DataController {
    private static DataController _instance;
    private Customer customer = new Customer();
    private List<Movie> movies = new ArrayList<>();

    public static DataController GetInstance ()
    {
        if (_instance == null) {
            _instance = new DataController ();
        }
        return _instance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}