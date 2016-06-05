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
    private List<Cinema> cinemas = new ArrayList<>();
    private Movie selectedMovie;
    private Cinema selectedCinema;

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

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    public Cinema getSelectedCinema() {
        return selectedCinema;
    }

    public void setSelectedCinema(Cinema selectedCinema) {
        this.selectedCinema = selectedCinema;
    }
}