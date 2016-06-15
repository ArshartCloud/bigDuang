package com.example.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by arshart on 2016/6/5 0005.
 */
public class DataController {
    private static DataController _instance;
    private Customer customer = new Customer();
    private List<Movie> movies = new ArrayList<>();
    private List<Cinema> cinemas = new ArrayList<>();
    private List<Showtime> showtimes = new ArrayList<>();
    private Movie selectedMovie;
    private Cinema selectedCinema;
    private Showtime selectedShowtime;
    private int selectedSeat;

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

    public int getSelectedSeat() {
        return selectedSeat;
    }

    public void setSelectedSeat(int selectedSeat) {
        this.selectedSeat = selectedSeat;
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    public Showtime getSelectedShowtime() {
        return selectedShowtime;
    }

    public void setSelectedShowtime(Showtime selectedShowtime) {
        this.selectedShowtime = selectedShowtime;
    }

    public void insertCinemaFromJSON(JSONArray jArr) {
        try{
            DataController.GetInstance().getCinemas().clear();
            for (int i = 0; i < jArr.length(); ++i) {
                JSONObject jo = jArr.getJSONObject(i);
                Cinema c = new Cinema();
                c.setId(jo.getInt("id"));
                c.setName(jo.getString("name"));
                c.setCity(jo.getString("city"));
                c.setAddress(jo.getString("address"));
                DataController.GetInstance().getCinemas().add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertMovieFromJSON(JSONArray jArr) {
        try{
            DataController.GetInstance().getMovies().clear();
            for (int i = 0; i < jArr.length(); ++i) {
                JSONObject jo = jArr.getJSONObject(i);
                Movie m = new Movie();
                m.setId(jo.getInt("id"));
                m.setName(jo.getString("name"));
                m.setImgName(jo.getString("img"));
                DataController.GetInstance().getMovies().add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertShowtimeFromJSON(JSONArray jArr) {
        try{
            DataController.GetInstance().getShowtimes().clear();
            for (int i = 0; i < jArr.length(); ++i) {
                JSONObject jo = jArr.getJSONObject(i);
                Showtime s = new Showtime();
                s.setId(jo.getInt("id"));
                s.setPrice(jo.getDouble("price"));
                JSONObject time = jo.getJSONObject("time");
                Date d = new Date(time.getLong("time"));
                s.setDate(d);
//                Log.i("234",d.toString());
                DataController.GetInstance().getShowtimes().add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}